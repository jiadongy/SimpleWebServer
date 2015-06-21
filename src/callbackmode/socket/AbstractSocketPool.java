package callbackmode.socket;

/**
 * Created by Feiyu on 2015/6/13 0013.
 * Socket连接池 没有找到 Java上的实现方法，WindowsAPI有
 * 放弃重用Socket
 */
@Deprecated
public abstract class AbstractSocketPool {
    private String name="AbstractSocketPool";

    public abstract void execute();

    public abstract void shutdown();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
