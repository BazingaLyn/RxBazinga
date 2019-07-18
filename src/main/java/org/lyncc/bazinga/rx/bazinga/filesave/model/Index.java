package org.lyncc.bazinga.rx.bazinga.filesave.model;

import lombok.ToString;

/**
 *
 * @author 莫那·鲁道
 * @date 2019-01-21-08:21
 */
@lombok.Data
@ToString
public class Index {

    String file_id;
    int value_sz;
    long value_pos;
    long timestamp;
}
