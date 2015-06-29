package busmode.messagebus.ver2.base;

import busmode.messagebus.ver2.BroadcastMessage;
import util.Utils;

/**
 * Created by Feiyu on 2015/6/29 0029.
 **/
public abstract class MessageReceiveHandler {

    public <U1> U1 process(SubscribePublishMessage message){
        Utils.log("SubscribePublishMessage Father");
        return null;
    };

    public <U2> U2 process(BroadcastMessage message){
        Utils.log("BroadcastMessage Father");
        return null;
    }

    public <U3> U3 process(ProducerCustomerMessage message){
        Utils.log("ProducerCustomerMessage Father");
        return null;
    }

    public <U4> U4 process(RequestResponseMessage message){
        Utils.log("RequestResponseMessage Father");
        return null;
    }

    public <U> U process(Message message){
        Utils.log("Message Father");
        return null;
    };

}
