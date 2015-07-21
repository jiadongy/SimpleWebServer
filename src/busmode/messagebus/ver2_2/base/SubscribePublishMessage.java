package busmode.messagebus.ver2_2.base;

import busmode.messagebus.ver2_2.IBusAgent;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Feiyu on 2015/6/28 0028.
 **/
public class SubscribePublishMessage<K> extends Message<K> {

    public SubscribePublishMessage(K data, ServiceType whichService) {
        super(data, whichService);
        getMetrics().setConstructorTime(System.nanoTime());
    }

    @Override
    public boolean isValid() {
        return getData() != null;
    }

    @Override
    public Collection<IBusAgent> getAgents(Collection<ServiceDescription> services) {
        ServiceType serviceProvided = this.getWhichService();
        Collection<IBusAgent> result = new ArrayList<>();
        for (ServiceDescription description : services) {
            if (description.getSubscribeTypes().contains(serviceProvided)
                    && !result.contains(description.getAgent())) {
                result.add(description.getAgent());
            }
        }
        return result;
    }
}
