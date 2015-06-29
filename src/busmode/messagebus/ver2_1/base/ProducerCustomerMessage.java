package busmode.messagebus.ver2_1.base;

/**
 * Created by Feiyu on 2015/6/29 0029.
 **/
public class ProducerCustomerMessage<K> extends Message<K> {
    public ProducerCustomerMessage(MessageType type, K data) {
        super(type, data);
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
