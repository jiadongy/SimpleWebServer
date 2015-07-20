package busmode.messagebus.ver2_2.base;

import busmode.messagebus.ver2_2.IBusAgent;

import java.util.Collection;

/**
 * Created by Feiyu on 2015/6/28 0028.
 */
public abstract class Message<K> {

    private ServiceType whichService;

    private MessageData<K> data;

    public Message(K data, ServiceType whichService) {
        this.whichService = whichService;
        this.data = new MessageData<>(data);
    }

    public abstract boolean isValid();

    public abstract Collection<IBusAgent> getAgents(
            Collection<ServiceDescription> services);


    public MessageData getData() {
        return data;
    }

    public void setData(MessageData data) {
        this.data = data;
    }

    public ServiceType getWhichService() {
        return whichService;
    }

    public void setWhichService(ServiceType whichService) {
        this.whichService = whichService;
    }
}
