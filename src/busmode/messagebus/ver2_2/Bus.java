package busmode.messagebus.ver2_2;

import busmode.messagebus.ver2_2.base.Message;
import busmode.messagebus.ver2_2.base.ServiceDescription;
import busmode.messagebus.ver2_2.base.ServiceRegisterOption;
import util.Utils;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Feiyu on 2015/6/28 0028.
 **/
public class Bus implements IBus {
    private static final Bus ourInstance = new Bus();

    public static Bus getInstance() {
        return ourInstance;
    }

    private Bus() {
    }

    private boolean isStopped = false;
    private final List<ServiceDescription> services = new LinkedList<>();
    private LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private long messagesSubmited = 0, totalDispatched = 0;
    private AtomicBoolean isDispatching = new AtomicBoolean(false);


    private int dispathThreads = 1;
    private List<Thread> threads = new ArrayList<>();
    private int drainNumber = 10;

    @Override
    public UUID register(IBusAgent agent, ServiceRegisterOption option) {
        boolean isExist = Utils.Lists.innerContains(services, agent,
                new Utils.Lists.InnerListEqual<ServiceDescription, IBusAgent>() {
                    @Override
                    public boolean equal(ServiceDescription element, IBusAgent obj) {
                        return element.getAgent() == obj;
                    }
                });
        if (!isExist) {
            ServiceDescription description =
                    new ServiceDescription(UUID.randomUUID(), agent, option);
            services.add(description);
            //Utils.log("Bus\tRegister success " + agent);
            return description.getServiceUUID();
        } else {
            //Utils.log("Bus\tRegister fail");
            return null;
        }
    }

    @Override
    public boolean unregister(UUID uuid) {
        int index = Utils.Lists.getFirstIndexInList(services, uuid,
                new Utils.Lists.InnerListEqual<ServiceDescription, UUID>() {
                    @Override
                    public boolean equal(ServiceDescription element, UUID obj) {
                        return element.getServiceUUID() == obj;
                    }
                });
        if (index >= 0) {
            services.remove(index);
            //Utils.log("Bus\tUnRegister success");
            return true;
        } else {
            //Utils.log("Bus\tUnRegister fail");
            return false;
        }
    }

    @Override
    public void submit(Message message) {
        if (message.isValid()) {
            try {
                queue.put(message);
                messagesSubmited++;
                message.getMetrics().setBusRecieveTime(System.currentTimeMillis());
                //Utils.log("Bus\tSend to Queue success : " + message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void dispatch() throws InterruptedException {
        Collection<Message> messages = new ArrayList<>();
        //Utils.log("Bus\tdispatch prepare to take element from MQ");
        messages.add(queue.take());
        isDispatching.set(true);
        queue.drainTo(messages, drainNumber - 1);
        for (Message message : messages) {
            Collection<IBusAgent> agents =
                    message.getAgents(services);
            for (IBusAgent agent : agents) {
                //message.getMetrics().setBusDispatchTime(System.currentTimeMillis());
                //Utils.log("Bus\tDispatch message " + message + " to " + agent.getAgentName());
                agent.receiveMessage(message);
                totalDispatched++;
            }
        }
        isDispatching.set(false);
    }

    private class DispatchThread extends Thread {
        @Override
        public void run() {
            try {
                //Utils.log("Bus\tstart to dispatch message");
                while (!isStopped) {
                    dispatch();
                    //Utils.log("Bus\tMessage left now\t\t"+queue.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stop() {
        this.isStopped = true;
        //Utils.log("Bus\tStopped");
        for (int i = 1; i <= dispathThreads; i++) {
            threads.get(i - 1).interrupt();
        }
    }

    @Override
    public void start() {
        this.isStopped = false;
        //Utils.log("Bus\tStarted");
        for (int i = 1; i <= dispathThreads; i++) {
            Thread t = new DispatchThread();
            threads.add(t);
            t.start();
        }
    }

    @Override
    public boolean isQueueEmptyAndDispatchStop() {
        return queue.isEmpty() && !isDispatching.get();
    }

    public long getTotalDispatched() {
        return totalDispatched;
    }

    public long getMessagesSubmited() {
        return messagesSubmited;
    }

    public void setDrainNumber(int drainNumber) {
        this.drainNumber = drainNumber;
    }

    public void setDispathThreads(int dispathThreads) {
        this.dispathThreads = dispathThreads;
    }

}
