package busmode.messagebus.ver2_2.base;

import java.util.Arrays;
import java.util.Vector;

/**
 * Created by Feiyu on 2015/6/28 0028.
 */
public class ServiceRegisterOption {

    private ServiceType publishType;

    private Vector<ServiceType> subscribeTypes;

    public ServiceRegisterOption(ServiceType publishType, ServiceType... subscribeTypes) {
        this.publishType = publishType;
        this.subscribeTypes = new Vector<>(Arrays.asList(subscribeTypes));
    }

    public ServiceRegisterOption(ServiceRegisterOption option) {
        this.publishType = option.publishType;
        this.subscribeTypes = option.subscribeTypes;
    }

    public ServiceType getPublishType() {
        return publishType;
    }

    public Vector<ServiceType> getSubscribeTypes() {
        return subscribeTypes;
    }
}
