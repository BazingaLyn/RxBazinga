package org.lyncc.bazinga.rx.bazinga.msentinel.context;

import org.lyncc.bazinga.rx.bazinga.msentinel.Constants;
import org.lyncc.bazinga.rx.bazinga.msentinel.EntryType;
import org.lyncc.bazinga.rx.bazinga.msentinel.log.RecordLog;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.node.EntranceNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.StringResourceWrapper;

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

    /**
     * Holds all entranceNode
     */
    private static volatile Map<String,DefaultNode> contextNameNodeMap = new HashMap<>();

    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final Context NULL_CONTEXT = new NullContext();

    public static Context getContext(){
        return contextHolder.get();
    }

    /**
     * 创建上下文
     * @param name
     * @param origin
     * @return
     */
    protected static Context trueEnter(String name,String origin){

        Context context = contextHolder.get();
        if(null == context){
            Map<String,DefaultNode> localCacheNameMap = contextNameNodeMap;
            DefaultNode node = localCacheNameMap.get(name);
            if(node == null){
                if(localCacheNameMap.size() > Constants.MAX_CONTEXT_NAME_SIZE){
                    setNullContext();
                    return NULL_CONTEXT;
                }else{
                    try{
                        LOCK.lock();
                        node = contextNameNodeMap.get(name);
                        if(node == null){
                            if(localCacheNameMap.size() > Constants.MAX_CONTEXT_NAME_SIZE){
                                setNullContext();
                                return NULL_CONTEXT;
                            }else{
                                node = new EntranceNode(new StringResourceWrapper(name, EntryType.IN),null);

                                //根节点
                                Constants.ROOT.addChild(node);
                                Map<String, DefaultNode> newMap = new HashMap<String, DefaultNode>(
                                        contextNameNodeMap.size() + 1);
                                newMap.putAll(contextNameNodeMap);
                                newMap.put(name, node);
                                contextNameNodeMap = newMap;
                            }
                        }
                    }finally {
                        LOCK.unlock();
                    }
                }

            }

        }

        return context;
    }

    private static boolean shouldWarn = true;

    private static void setNullContext() {
        contextHolder.set(NULL_CONTEXT);
        // Don't need to be thread-safe.
        if (shouldWarn) {
            RecordLog.warn("[SentinelStatusChecker] WARN: Amount of context exceeds the threshold "
                    + Constants.MAX_CONTEXT_NAME_SIZE + ". Entries in new contexts will NOT take effect!");
            shouldWarn = false;
        }
    }
}
