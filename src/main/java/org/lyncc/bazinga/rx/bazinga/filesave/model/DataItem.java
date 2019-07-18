package org.lyncc.bazinga.rx.bazinga.filesave.model;


/**
 *
 * @author 莫那·鲁道
 * @date 2019-01-21-08:20
 */
@lombok.Data
public class DataItem {

    String crc;
    long timestamp;
    int key_sz;
    int value_sz;
    byte[] key;
    byte[] value;

}
