package org.lyncc.bazinga.rx.bazinga.msentinel.context;

import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ContextUtil
 *
 * @author liguolin
 * @create 2018-12-25 13:43
 **/
public class ContextUtil {

    private static ThreadLocal<Context> contextHolder = new ThreadLocal<>();

    private static volatile Map<String,DefaultNode> contextNameNodeMap = new HashMap<>();

    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final Context NULL_CONTEXT = new NullContext();

    public static Context getContext(){
        return contextHolder.get();
    }
}
