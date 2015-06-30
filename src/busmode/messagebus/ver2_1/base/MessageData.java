package busmode.messagebus.ver2_1.base;

/**
 * Created by Feiyu on 2015/6/30 0030.
 **/
public class MessageData<K> {

    private K data;

    private Class dataClass;

    public MessageData(K data) {
        this.data = data;
        this.dataClass = data.getClass();
    }

    public K getData() {
        return data;
    }

    public void setData(K data) {
        this.data = data;
    }

    public Class getDataClass() {
        return dataClass;
    }

    public void setDataClass(Class dataClass) {
        this.dataClass = dataClass;
    }
}
