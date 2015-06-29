package busmode.messagebus.ver2_1;

import busmode.messagebus.ver2_1.base.Message;
import busmode.messagebus.ver2_1.base.ServiceRegisterOption;

/**
 * Created by Feiyu on 2015/6/28 0028.
 **/
public interface IBusAgent {

    boolean register(ServiceRegisterOption option);

    boolean unregister();

    void submit(Message message);

    void receiveMessage(Message message);

    void takeMultipleAndProcess(int number) throws InterruptedException;

}
