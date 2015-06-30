package busmode.messagebus.ver2_1.base;

/**
 * Created by Feiyu on 2015/6/28 0028.
 **/
public class SubscribePublishMessage<K> extends Message<K> {

    public SubscribePublishMessage(K data ,ServiceType whichService) {
        super(data,whichService);
    }

    @Override
    public boolean isValid() {
        return getData() != null;
    }
}
