package busmode.messagebus.ver2_2.base;

import busmode.messagebus.ver2_2.IBusAgent;

import java.util.UUID;

/**
 * Created by Feiyu on 2015/6/28 0028.
 */
public class ServiceDescription extends ServiceRegisterOption {

    private UUID serviceUUID;
    private IBusAgent agent;

    public ServiceDescription(UUID serviceUUID,
                              IBusAgent agnet,
                              ServiceRegisterOption option) {
        super(option);
        this.serviceUUID = serviceUUID;
        this.agent = agnet;
    }

    public UUID getServiceUUID() {
        return serviceUUID;
    }

    public IBusAgent getAgent() {
        return agent;
    }


}
