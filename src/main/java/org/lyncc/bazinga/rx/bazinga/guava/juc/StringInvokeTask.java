package org.lyncc.bazinga.rx.bazinga.guava.juc;

/**
 * 字符串执行任务
 *
 * @author liguolin
 * @create 2018-01-16 17:17
 **/
public class StringInvokeTask extends AbstractInvokeTask<String> {

    @Override
    public String call() throws Exception {

        Thread.sleep(5000l);
        return "hello guava world";
    }
}
