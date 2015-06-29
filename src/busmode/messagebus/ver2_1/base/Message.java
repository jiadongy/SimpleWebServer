package busmode.messagebus.ver2_1.base;

/**
 * Created by Feiyu on 2015/6/28 0028.
 */
public abstract class Message<K> {

    private MessageType type;

    private K data;

    private Class<?> dataClass;

    public Message(MessageType type, K data) {
        this.type = type;
        this.data = data;
        this.dataClass = data.getClass();
    }

    public abstract boolean isValid();

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public K getData() {
        return data;
    }

    public void setData(K data) {
        this.data = data;
    }

    public Class<?> getDataClass() {
        return dataClass;
    }
}
