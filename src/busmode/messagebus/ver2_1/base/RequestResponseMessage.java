package busmode.messagebus.ver2_1.base;

/**
 * Created by Feiyu on 2015/6/29 0029.
 **/
public class RequestResponseMessage<K> extends Message<K> {
    public RequestResponseMessage(K data ,ServiceType whichService) {
        super(data, whichService);
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
