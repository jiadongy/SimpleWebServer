package busmode.messagebus.ver2.base;

import busmode.messagebus.ver2.BusAgent;

import java.util.UUID;

/**
 * Created by Feiyu on 2015/6/28 0028.
 */
public class ServiceDescription extends ServiceRegisterOption{

    private UUID serviceUUID;
    private BusAgent agent;

    public ServiceDescription(UUID serviceUUID,
                              BusAgent agnet,
                              ServiceRegisterOption option) {
        super(option);
        this.serviceUUID = serviceUUID;
        this.agent = agnet;
    }

    public UUID getServiceUUID() {
        return serviceUUID;
    }

    public BusAgent getAgent() {
        return agent;
    }


}
