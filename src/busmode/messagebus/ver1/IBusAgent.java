package busmode.messagebus.ver1;

import busmode.messagebus.ver1.base.Message;

import java.util.UUID;

/**
 * Created by Feiyu on 2015/6/27 0027.
 */
public interface IBusAgent {

    void register(String serviceType, String... serviceAccepts);

    void unregister(UUID uuid);

    <K> void send(K data, String... accepts);

    void notify(Message message);
}
