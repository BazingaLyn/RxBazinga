package org.lyncc.bazinga.rx.bazinga.filesave;


import org.lyncc.bazinga.rx.bazinga.filesave.internal.StorageKernel;

/**
 *
 * @author 莫那·鲁道
 * @date 2019-01-21-08:32
 */
public class BitcaskDB {

    StorageKernel kernel = StorageKernel.getInstance();

    private BitcaskDB() {
    }

    public static BitcaskDB getInstance() {
        return BitcaskDBLazyHolder.INSTANCE;
    }

    private static class BitcaskDBLazyHolder {

        private static final BitcaskDB INSTANCE = new BitcaskDB();
    }


    public static BitcaskDB load() {
        return BitcaskDB.getInstance();
    }

    public void put(String key, String value) {
        kernel.put(key.getBytes(), value.getBytes());
    }

    public void del(String key) {
        kernel.del(key.getBytes());
    }

    public void update(String key, String newVal) {
        kernel.update(key.getBytes(), newVal.getBytes());
    }

    public String get(String key) {
        return kernel.get(key.getBytes());
    }
}
