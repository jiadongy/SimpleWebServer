package busmode.messagebus.ver1;

import busmode.messagebus.ver1.base.Message;
import busmode.messagebus.ver1.base.NodeInfo;
import busmode.messagebus.ver1.base.ServiceOption;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by Feiyu on 2015/6/27 0027.
 */
public class Bus implements IBus{
    private static Bus ourInstance = new Bus();

    public static Bus getInstance() {
        return ourInstance;
    }

    private Bus() {
    }

    private ArrayList<NodeInfo> nodes = new ArrayList<>();

    @Override
    public UUID register(ServiceOption option,BusAgent agent){
        boolean canAdd = true;
        Iterator<NodeInfo> iter = nodes.iterator();
        while(iter.hasNext()){
            NodeInfo info = iter.next();
            if(info.getAgent() == agent){
                canAdd = false;
                break;
            }
        }
        if(canAdd){
            NodeInfo info = new NodeInfo(UUID.randomUUID(),
                    option.getTypeProvide(),
                    option.getTypeAccept(),
                    agent);
            nodes.add(info);
            System.out.println("Bus:register success with agent " + agent);
            return info.getUuid();
        }
        System.out.println("Bus:register fail with repeat");
        return null;

    }

    @Override
    public void unregister(UUID uuid) {
        boolean canRemove = true;
        Iterator<NodeInfo> iter = nodes.iterator();
        while(iter.hasNext()){
            NodeInfo info = iter.next();
            nodes.remove(info);
            canRemove = true;
            System.out.println("Bus:unregister success "+uuid);

            break;
        }
        if(!canRemove)
            System.out.println("Bus:unregister success "+uuid);
    }

    @Override
    public void send(Message message) {
        boolean canSend = false;
        Iterator<NodeInfo> iter = nodes.iterator();
        while(iter.hasNext()){
            NodeInfo node = iter.next();
            for(Object receiveType : message.getReceiverTypes()){
                if(node.getTypeAccept().contains(receiveType)){
                    canSend = true;
                    notify(node.getAgent(), message);
                    System.out.println("Bus:send success to " + node.getAgent());
                }
            }
        }
        if(!canSend)
            System.out.println("Bus:send fail");
    }

    @Override
    public void notify(IBusAgent agent,Message message) {
        agent.notify(message);
    }


}
