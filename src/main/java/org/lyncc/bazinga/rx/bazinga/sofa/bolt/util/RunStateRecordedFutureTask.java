package org.lyncc.bazinga.rx.bazinga.sofa.bolt.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author liguolin
 * @create 2018-07-27 16:59
 **/
public class RunStateRecordedFutureTask<V> extends FutureTask<V> {

    private AtomicBoolean hasRun = new AtomicBoolean();

    public RunStateRecordedFutureTask(Callable<V> callable) {
        super(callable);
    }

    @Override
    public void run() {
        this.hasRun.set(true);
        super.run();
    }

    public V getAfterRun() throws InterruptedException, ExecutionException,
            FutureTaskNotRunYetException {
        if (!hasRun.get()) {
            throw new FutureTaskNotRunYetException();
        }
        return super.get();
    }
}
