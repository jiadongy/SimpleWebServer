package busmode.messagebus.ver1.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Feiyu on 2015/6/27 0027.
 */
public class ServiceOption {

    private String typeProvide;

    private List<String> typeAccept;


    public ServiceOption(String typeProvide, String... typeAccept) {
        this.typeProvide = typeProvide;

        this.typeAccept = new ArrayList<>();
        this.typeAccept.addAll(Arrays.asList(typeAccept));
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
