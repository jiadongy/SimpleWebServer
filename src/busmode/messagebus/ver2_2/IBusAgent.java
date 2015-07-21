package busmode.messagebus.ver2_2;

import busmode.messagebus.ver2_2.base.Message;
import busmode.messagebus.ver2_2.base.MessageData;
import busmode.messagebus.ver2_2.base.ServiceRegisterOption;
import busmode.messagebus.ver2_2.base.ServiceType;
import util.Pair;

import java.util.Collection;

/**
 * Created by Feiyu on 2015/6/28 0028.
 **/
public interface IBusAgent {

    boolean register(ServiceRegisterOption option);

    boolean unregister();

    void submit(Message message);

    void receiveMessage(Message message);

    Collection<Pair<ServiceType, MessageData>> takeMultiple(int number) throws InterruptedException;

    String getAgentName();
}
