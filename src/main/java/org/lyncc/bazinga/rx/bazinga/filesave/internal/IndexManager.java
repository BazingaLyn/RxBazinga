package org.lyncc.bazinga.rx.bazinga.filesave.internal;

import cn.thinkinjava.model.Index;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author 莫那·鲁道
 * @date 2019-01-21-08:57
 */
@Data
public class IndexManager {

    public Map<String, Index> indexHashMap = new ConcurrentHashMap<String, Index>();

    private IndexManager() {
    }

    public static IndexManager getInstance() {
        return IndexManagerLazyHolder.INSTANCE;
    }

    private static class IndexManagerLazyHolder {

        private static final IndexManager INSTANCE = new IndexManager();
    }

    public void put(byte[] key, Index index) {
        this.indexHashMap.put(new String(key), index);
    }

    public Index get(String key) {
        return indexHashMap.get(key);
    }

    public Index get(byte[] key) {
        return indexHashMap.get(new String(key));
    }

    public void remove(byte[] key) {
        indexHashMap.remove(key);
    }
}
