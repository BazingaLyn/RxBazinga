package org.lyncc.bazinga.rx.bazinga.msentinel.slots.statistic;

import org.lyncc.bazinga.rx.bazinga.msentinel.node.DefaultNode;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ProcessorSlotEntryCallback;
import org.lyncc.bazinga.rx.bazinga.msentinel.slotchain.ProcessorSlotExitCallback;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liguolin
 * @create 2018-12-28 18:29
 **/
public final class StatisticSlotCallbackRegistry {


    private static final Map<String,ProcessorSlotEntryCallback<DefaultNode>> entryCallbackMap = new ConcurrentHashMap<>();

    private static final Map<String,ProcessorSlotExitCallback> exitCallbackMap = new ConcurrentHashMap<>();

    public static void clearEntryCallback(){
        entryCallbackMap.clear();
    }

    public static void clearExitCallback(){
        exitCallbackMap.clear();
    }

    public static void addEntryCallback(String key, ProcessorSlotEntryCallback<DefaultNode> callback) {
        entryCallbackMap.put(key, callback);
    }

    public static void addExitCallback(String key, ProcessorSlotExitCallback callback) {
        exitCallbackMap.put(key, callback);
    }

    public static ProcessorSlotEntryCallback<DefaultNode> removeEntryCallback(String key) {
        if (key == null) {
            return null;
        }
        return entryCallbackMap.remove(key);
    }

    public static ProcessorSlotExitCallback removeExitCallback(String key) {
        if (key == null) {
            return null;
        }
        return exitCallbackMap.remove(key);
    }

    public static Collection<ProcessorSlotEntryCallback<DefaultNode>> getEntryCallbacks() {
        return entryCallbackMap.values();
    }

    public static Collection<ProcessorSlotExitCallback> getExitCallbacks() {
        return exitCallbackMap.values();
    }

    private StatisticSlotCallbackRegistry() {}


}
