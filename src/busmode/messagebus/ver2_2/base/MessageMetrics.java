package busmode.messagebus.ver2_2.base;

/**
 * Created by Feiyu on 2015/7/21.
 **/
public class MessageMetrics {
    private long constructorTime = 0;
    private long busRecieveTime = 0;
    private long busDispatchTime = 0;
    private long agentRecieveTime = 0;

    public MessageMetrics() {
        this.constructorTime = System.currentTimeMillis();
    }

    public long getConstructorTime() {
        return constructorTime;
    }

    public void setConstructorTime(long constructorTime) {
        this.constructorTime = constructorTime;
    }

    public long getBusRecieveTime() {
        return busRecieveTime;
    }

    public void setBusRecieveTime(long busRecieveTime) {
        this.busRecieveTime = busRecieveTime;
    }

    public long getBusDispatchTime() {
        return busDispatchTime;
    }

    public void setBusDispatchTime(long busDispatchTime) {
        this.busDispatchTime = busDispatchTime;
    }

    public long getAgentRecieveTime() {
        return agentRecieveTime;
    }

    public void setAgentRecieveTime(long agentRecieveTime) {
        this.agentRecieveTime = agentRecieveTime;
    }
}
