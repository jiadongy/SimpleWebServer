package busmode.messagebus.ver1.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by Feiyu on 2015/6/27 0027.
 */
public class Message<K> {

    private UUID sender;
    private List<String> receiverTypes = new ArrayList<>();
    private K data;

    public Message(UUID sender, K data, String... receiverTypes) {
        this.sender = sender;
        this.receiverTypes.addAll(Arrays.asList(receiverTypes));
        this.data = data;
    }

    public UUID getSender() {
        return sender;
    }

    public void setSender(UUID sender) {
        this.sender = sender;
    }

    public List<String> getReceiverTypes() {
        return receiverTypes;
    }

    public void setReceiverTypes(List<String> receiverTypes) {
        this.receiverTypes = receiverTypes;
    }

    public K getData() {
        return data;
    }

    public void setData(K data) {
        this.data = data;
    }
}
