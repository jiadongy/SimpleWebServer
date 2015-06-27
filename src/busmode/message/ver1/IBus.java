package busmode.message.ver1;

import busmode.message.ver1.base.Message;
import busmode.message.ver1.base.ServiceOption;

import java.util.UUID;

/**
 * Created by Feiyu on 2015/6/27 0027.
 */
public interface IBus {
    UUID register(ServiceOption option,BusAgent agent);

    void unregister(UUID uuid);

    void send(Message message);

    void notify(IBusAgent agent,Message message);
}
