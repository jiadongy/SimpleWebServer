package busmode.messagebus.ver2;

import busmode.messagebus.ver2.base.*;
import util.Utils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Feiyu on 2015/6/28 0028.
 */
public class BusAgent implements IBusAgent{

    private BlockingQueue<Message> messageBuffer = new LinkedBlockingQueue<>();

    private IService instance;
    private IBus bus = Bus.getInstance();
    private UUID uuid;
    private MessageReceiveHandler handler;

    public BusAgent(IService instance, MessageReceiveHandler handler) {
        this.instance = instance;
        this.handler = handler;
    }

    @Override
    public boolean register(ServiceRegisterOption option){
        uuid = bus.register(this, option);
        boolean status = uuid != null;
        Utils.log("BusAgent:register status: "+status);
        return status;
    }

    @Override
    public boolean unregister(){
        boolean status = false;
        if(this.uuid != null){
            bus.unregister(uuid);
            status = true;
        }
        Utils.log("BusAgent:register status: " + status);
        return status;
    }

    @Override
    public void submit(Message message){
        bus.submit(message);
    }

    @Override
    public void receiveMessage(Message message) {
        if(messageBuffer.offer(message))
            Utils.log("BusAgent:receive success " );
        else
            Utils.log("BusAgent:receive fail " );
    }

    @Override
    public void takeMultipleAndProcess(int number)
            throws InterruptedException {
        Collection<Message> messages = new LinkedList<>();
        messages.add(messageBuffer.take());
        messageBuffer.drainTo(messages, number - 1);
        Utils.log("BusAgent: take message size"+messages.size());
        for(Message message : messages){
            Utils.log("BusAgent: prepare to process "+message);
            handler.process(message);
        }
    }

}
