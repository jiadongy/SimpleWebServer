package busmode.messagebus.ver2_1.base;

/**
 * Created by Feiyu on 2015/6/28 0028.
 */
public final class ServiceType  {
    private String type;

    public ServiceType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceType that = (ServiceType) o;

        return type.equals(that.type);

    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

    @Override
    public String toString() {
        return type;
    }
}
