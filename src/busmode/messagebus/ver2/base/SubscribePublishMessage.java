package busmode.messagebus.ver2.base;

/**
 * Created by Feiyu on 2015/6/28 0028.
 **/
public class SubscribePublishMessage<K> extends Message<K> {


    private ServiceType publishType;

    public SubscribePublishMessage(K data, ServiceType publishType) {
        super(MessageType.Subscribe_Publish, data);
        this.publishType = publishType;
    }

    public ServiceType getPublishType() {
        return publishType;
    }

    public void setPublishType(ServiceType publishType) {
        this.publishType = publishType;
    }

    @Override
    public boolean isValid() {
        return publishType != null
                && getData() != null;
    }
}
