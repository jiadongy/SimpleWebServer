package busmode.messagebus.ver2_2.base;

import busmode.messagebus.ver2_2.IBusAgent;

import java.util.Collection;

/**
 * Created by Feiyu on 2015/6/29 0029.
 **/
public class ProducerCustomerMessage<K> extends Message<K> {
    public ProducerCustomerMessage(K data, ServiceType whichService) {
        super(data, whichService);
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public Collection<IBusAgent> getAgents(Collection<ServiceDescription> services) {
        return null;
    }
}
