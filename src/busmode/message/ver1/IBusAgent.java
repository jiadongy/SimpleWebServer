package busmode.message.ver1;

import busmode.message.ver1.base.Message;

import java.util.UUID;

/**
 * Created by Feiyu on 2015/6/27 0027.
 */
public interface IBusAgent {

    public void register(String serviceType,String... serviceAccepts);

    public void unregister(UUID uuid);

    public <K> void send(K data,String... accepts);

    public void notify(Message message);
}
