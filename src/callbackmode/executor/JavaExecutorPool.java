package callbackmode.executor;

import java.util.concurrent.*;

/**
 * Created by Feiyu on 2015/6/15 0015.
 */
@Deprecated
public class JavaExecutorPool<K> extends AbstractExecutorPool<K> {

    private ExecutorService service= Executors.newFixedThreadPool(100);

    @Override
    public Future<K> execute(Callable<K> callable) {
        return service.submit(callable);
    }

    @Override
    public void shutdown() {
        service.shutdown();
    }
}
