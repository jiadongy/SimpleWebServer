package callbackmode.socket;

/**
 * Created by Feiyu on 2015/6/13 0013.
 * Socket���ӳ� û���ҵ� Java�ϵ�ʵ�ַ�����WindowsAPI��
 * ��������Socket
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
