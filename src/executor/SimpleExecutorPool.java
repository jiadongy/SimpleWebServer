package executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * Created by Feiyu on 2015/6/15 0015.
 */
public class SimpleExecutorPool<K> extends AbstractExecutorPool<K> {
    private ConcurrentHashMap<Integer,Callable<K>> hashmap=new ConcurrentHashMap<>();
    private ArrayBlockingQueue<Callable<K>> queue=new ArrayBlockingQueue<Callable<K>>(10);

    private ArrayBlockingQueue<Thread> threadFreePool=new ArrayBlockingQueue<Thread>(10);

    @Override
    public Future<K> execute(Callable<K> callable) {
        queue.add(callable);
        return null;
    }

    @Override
    public void shutdown() {

    }

    public void remove(Callable callable){

    }
}
