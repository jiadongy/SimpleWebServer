package busmode.message.ver1;

import busmode.message.ver1.base.Message;
import busmode.message.ver1.base.ServiceOption;

import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Feiyu on 2015/6/27 0027.
 */
public class BusAgent implements IBusAgent{

    private IBus bus = Bus.getInstance();

    private IService realService;

    private UUID uuid;

    public BusAgent(IService realService) {
        this.realService = realService;
    }

    @Override
    public void register(String serviceType,String... serviceAccepts) {
        ServiceOption option = new ServiceOption(serviceType,serviceAccepts);
        uuid = bus.register(option,this);
    }

    @Override
    public void unregister(UUID uuid) {
        bus.unregister(uuid);
    }

    @Override
    public <K> void send(K data, String... accepts) {
        Message<K> message = new Message<>(uuid,data,accepts);
        bus.send(message);
    }

    public void notify(Message message) {
        realService.getMessage(message);
    }


}
