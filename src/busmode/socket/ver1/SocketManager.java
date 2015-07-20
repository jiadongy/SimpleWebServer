package busmode.socket.ver1;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Feiyu on 2015/6/30 0030.
 **/
public abstract class SocketManager {

    protected final int PORT = 8888;

    protected Charset charset = Charset.forName("UTF-8");

    protected InetSocketAddress bindAddress = new InetSocketAddress(PORT);

    protected ExecutorService pool;

    public SocketManager(ExecutorService pool) {
        this.pool = pool;
    }

    public SocketManager() {
        this.pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    }
}
