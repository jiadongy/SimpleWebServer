package busmode.messagebus.ver2;

import busmode.messagebus.ver2.base.*;
import util.Utils;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/** Created by Feiyu on 2015/6/28 0028. **/
public class Bus implements IBus{
    private static final Bus ourInstance = new Bus();

    public static Bus getInstance() {
        return ourInstance;
    }

    private Bus() {
    }

    private boolean isStopped = false;
    private final List<ServiceDescription> services = new LinkedList<>();
    private BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    @Override
    public UUID register(BusAgent agent,ServiceRegisterOption option){
        boolean isExist = Utils.Lists.innerContains(services, agent,
                new Utils.Lists.InnerListEqual<ServiceDescription, BusAgent>() {
                    @Override
                    public boolean equal(ServiceDescription element, BusAgent obj) {
                        return element.getAgent() == obj;
                    }
                });
        if(!isExist){
            ServiceDescription description =
                    new ServiceDescription(UUID.randomUUID(), agent, option);
            services.add(description);
            Utils.log("Bus:register success "+agent);
            return description.getServiceUUID();
        }else{
            Utils.log("Bus:register fail,BusAgent existed");
            return null;
        }
    }

    @Override
    public boolean unregister(UUID uuid){
        int index = Utils.Lists.getFirstIndexInList(services, uuid,
                new Utils.Lists.InnerListEqual<ServiceDescription, UUID>() {
                    @Override
                    public boolean equal(ServiceDescription element, UUID obj) {
                        return element.getServiceUUID() == obj;
                    }
                });
        if(index >= 0){
            services.remove(index);
            Utils.log("Bus:unregister success");
            return true;
        }else{
            Utils.log("Bus:unregister fail,UUID not existed");
            return false;
        }
    }

    @Override
    public void submit(Message message){
        if(message.isValid()){
            try {
                queue.put(message);
                Utils.log("Bus:Send to Queue success , notify to dispatch "+message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void dispatch() throws InterruptedException {
        Collection<Message> messages = new ArrayList<>();
        Utils.log("Bus:dispatch prepare to take element from MQ");
        messages.add(queue.take());
        queue.drainTo(messages, 9);
        for(Message message : messages){
            switch (message.getType()){
                case Subscribe_Publish:
                    Collection<BusAgent> agents =
                            getAgentsForSubscribePublishMessage(
                                    (SubscribePublishMessage)message);
                    for(BusAgent agent : agents){
                        agent.receiveMessage(message);
                        Utils.log("Bus:dispatch send message "+message+" to "+agent);
                    }
                    break;
                default:
                    Utils.log("Bus:dispatch fail wrong message type");
            }
        }

    }

    private Thread busThread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Utils.log("Bus:start to dispatch message");
                while (!isStopped) {
                    dispatch();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    });

    @Override
    public void stop(){
        this.isStopped = true;
        Utils.log("Bus:stopped");
    }

    @Override
    public void start() {
        this.isStopped = false;
        Utils.log("Bus:started");
        this.busThread.start();
    }

    private Collection<BusAgent> getAgentsForSubscribePublishMessage
            (SubscribePublishMessage message){
        ServiceType publishType = message.getPublishType();
        Collection<BusAgent> result = new ArrayList<>();
        for(ServiceDescription description : services){
            if(description.getSubscribeTypes().contains(publishType)
                    && !result.contains(description.getAgent())){
                result.add(description.getAgent());
            }
        }
        return result;
    }





}
