package callbackmode.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by Feiyu on 2015/6/15 0015.
 */
@Deprecated
public abstract class AbstractExecutorPool<K> {
    private String name="ExecutorPool";

    void AbstractExecutorPool(String name){
        this.name=name;
    }

    abstract public Future<K> execute(Callable<K> callable);

    abstract public void shutdown();

}
