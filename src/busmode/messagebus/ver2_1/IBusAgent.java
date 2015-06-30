package busmode.messagebus.ver2_1;

import busmode.messagebus.ver2.base.ServiceType;
import busmode.messagebus.ver2_1.base.Message;
import busmode.messagebus.ver2_1.base.MessageData;
import busmode.messagebus.ver2_1.base.ServiceRegisterOption;
import util.Pair;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Feiyu on 2015/6/28 0028.
 **/
public interface IBusAgent {

    boolean register(ServiceRegisterOption option);

    boolean unregister();

    void submit(Message message);

    void receiveMessage(Message message);

    Collection<Pair<busmode.messagebus.ver2_1.base.ServiceType, MessageData>> takeMultiple(int number) throws InterruptedException;

}
