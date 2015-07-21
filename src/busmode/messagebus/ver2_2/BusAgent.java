package busmode.messagebus.ver2_2;

import busmode.messagebus.ver2_2.base.Message;
import busmode.messagebus.ver2_2.base.MessageData;
import busmode.messagebus.ver2_2.base.ServiceRegisterOption;
import busmode.messagebus.ver2_2.base.ServiceType;
import util.Pair;
import util.Utils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Feiyu on 2015/6/28 0028.
 */
public class BusAgent implements IBusAgent {

    private BlockingQueue<Message> messageBuffer = new LinkedBlockingQueue<>();

    private IBus bus = Bus.getInstance();
    private UUID uuid;

    private String agentName;

    public BusAgent(String name) {
        this.agentName = name;
    }

    @Override
    public boolean register(ServiceRegisterOption option) {
        uuid = bus.register(this, option);
        boolean status = uuid != null;
        //Utils.log(agentName+"\tRegister status: " + status);
        return status;
    }

    @Override
    public boolean unregister() {
        boolean status = false;
        if (this.uuid != null) {
            bus.unregister(uuid);
            status = true;
        }
        //Utils.log(agentName+"\tUnRegister status: " + status);
        return status;
    }

    @Override
    public void submit(Message message) {
        bus.submit(message);
    }

    @Override
    public void receiveMessage(Message message) {
        message.getMetrics().setAgentRecieveTime(System.currentTimeMillis());
        if (messageBuffer.offer(message)) {
            Utils.log(agentName + "\t" + message + "\t" +
                    (message.getMetrics().getAgentRecieveTime() - message.getMetrics().getBusRecieveTime()));
        }//else
        // Utils.log(agentName+"\tReceive Message fail");
    }

    @Override
    public Collection<Pair<ServiceType, MessageData>> takeMultiple(int number)
            throws InterruptedException {
        Collection<Pair<ServiceType, MessageData>> results = new LinkedList<>();
        Collection<Message> messages = new LinkedList<>();
        messages.add(messageBuffer.take());
        messageBuffer.drainTo(messages, number - 1);
        //Utils.log(agentName+"\tTake message size " + messages.size());
        for (Message message : messages) {
            results.add(new Pair<>(message.getWhichService(),
                    message.getData()));
        }
        return results;
    }

    @Override
    public String getAgentName() {
        return agentName;
    }

}
