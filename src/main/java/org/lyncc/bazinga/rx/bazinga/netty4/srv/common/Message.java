package org.lyncc.bazinga.rx.bazinga.netty4.srv.common;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liguolin
 * @create 2018-07-16 11:31
 **/
public class Message {

    private static final AtomicLong sequenceGenerator = new AtomicLong(0);

    /**
     * 请求序号
     */
    private final long sequence;

    /**
     * 请求标志位
     */
    private short sign;

    /**
     * 版本号
     */
    private long version;

    /**
     * 实际传输数据
     */
    private Object data;

    public Message() {
        this(sequenceGenerator.getAndIncrement());
    }

    public Message(long sequence) {
        this.sequence = sequence;
    }

    public long sequence() {
        return sequence;
    }

    public short sign() {
        return sign;
    }

    public void sign(short sign) {
        this.sign = sign;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Object data() {
        return data;
    }

    public void data(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
