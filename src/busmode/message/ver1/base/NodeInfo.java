package busmode.message.ver1.base;

import busmode.message.ver1.BusAgent;

import java.util.List;
import java.util.UUID;

/**
 * Created by Feiyu on 2015/6/27 0027.
 */
public class NodeInfo {

    private UUID uuid;
    private String typeProvide;
    private List<String> typeAccept;
    private BusAgent agent;

    public NodeInfo(UUID uuid, String typeProvide,
                    List<String> typeAccept, BusAgent agent) {
        this.uuid = uuid;
        this.typeProvide = typeProvide;
        this.typeAccept = typeAccept;
        this.agent = agent;
    }

    public BusAgent getAgent() {
        return agent;
    }

    public void setAgent(BusAgent agent) {
        this.agent = agent;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTypeProvide() {
        return typeProvide;
    }

    public void setTypeProvide(String typeProvide) {
        this.typeProvide = typeProvide;
    }

    public List<String> getTypeAccept() {
        return typeAccept;
    }

    public void setTypeAccept(List<String> typeAccept) {
        this.typeAccept = typeAccept;
    }
}
