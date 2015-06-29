package busmode.messagebus.ver2_1;

import busmode.messagebus.ver2_1.base.Message;
import busmode.messagebus.ver2_1.base.ServiceRegisterOption;

import java.util.UUID;

/**
 * Created by Feiyu on 2015/6/28 0028.
 **/
public interface IBus {

    UUID register(BusAgent agent, ServiceRegisterOption option);

    boolean unregister(UUID uuid);

    void submit(Message message);

    void stop();

    void start();
}
