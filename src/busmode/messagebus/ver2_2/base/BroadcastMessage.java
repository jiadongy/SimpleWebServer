package busmode.messagebus.ver2_2.base;

import busmode.messagebus.ver1.base.Message;

import java.util.UUID;

/**
 * Created by Feiyu on 2015/6/29 0029.
 **/
public class BroadcastMessage<K> extends Message<K> {
    public BroadcastMessage(UUID sender, K data, String... receiverTypes) {
        super(sender, data, receiverTypes);
    }
}
