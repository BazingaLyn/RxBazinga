package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.rpc.RpcProtocolV2;
import org.lyncc.bazinga.rx.bazinga.sofa.bolt.util.ConcurrentHashSet;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liguolin
 * @create 2018-07-18 17:05
 **/
public class Connection {

    private Channel channel;

    private final ConcurrentHashMap<Integer, InvokeFuture> invokeFutureMap  = new ConcurrentHashMap<Integer, InvokeFuture>(
            4);

    /** Attribute key for connection */
    public static final AttributeKey<Connection> CONNECTION       = AttributeKey
            .valueOf("connection");
    /** Attribute key for heartbeat count */
    public static final AttributeKey<Integer>                                     HEARTBEAT_COUNT  = AttributeKey
            .valueOf("heartbeatCount");

    /** Attribute key for heartbeat switch for each connection */
    public static final AttributeKey<Boolean>                                     HEARTBEAT_SWITCH = AttributeKey
            .valueOf("heartbeatSwitch");

    /** Attribute key for protocol */
    public static final AttributeKey<ProtocolCode>                                PROTOCOL         = AttributeKey
            .valueOf("protocol");
    private ProtocolCode                                                          protocolCode;

    /** Attribute key for version */
    public static final AttributeKey<Byte>                                        VERSION          = AttributeKey
            .valueOf("version");
    private byte                                                                  version          = RpcProtocolV2.PROTOCOL_VERSION_1;

    private Url                                                                   url;

    private Set<String> poolKeys         = new ConcurrentHashSet<String>();

    private AtomicBoolean closed           = new AtomicBoolean(
            false);

    private final ConcurrentHashMap<String/* attr key*/, Object /*attr value*/> attributes       = new ConcurrentHashMap<String, Object>();

    /** the reference count used for this connection. If equals 2, it means this connection has been referenced 2 times */
    private final AtomicInteger referenceCount   = new AtomicInteger();

    /** no reference of the current connection */
    private static final int                                                      NO_REFERENCE     = 0;

    private final ConcurrentHashMap<Integer/* id */, String/* poolKey */>       id2PoolKey       = new ConcurrentHashMap<Integer, String>(
            256);


    /**
     * Whether invokeFutures is completed
     *
     */
    public boolean isInvokeFutureMapFinish() {
        return invokeFutureMap.isEmpty();
    }

    /**
     * add a pool key to list
     *
     * @param poolKey
     */
    public void addPoolKey(String poolKey) {
        poolKeys.add(poolKey);
    }

    /**
     * get all pool keys
     */
    public Set<String> getPoolKeys() {
        return new HashSet<String>(poolKeys);
    }

    /**
     * remove pool key
     *
     * @param poolKey
     */
    public void removePoolKey(String poolKey) {
        poolKeys.remove(poolKey);
    }

    /**
     * Getter method for property <tt>url</tt>.
     *
     * @return property value of url
     */
    public Url getUrl() {
        return url;
    }

    /**
     * add Id to group Mapping
     *
     * @param id
     * @param poolKey
     */
    public void addIdPoolKeyMapping(Integer id, String poolKey) {
        this.id2PoolKey.put(id, poolKey);
    }

    /**
     * remove id to group Mapping
     *
     * @param id
     * @return
     */
    public String removeIdPoolKeyMapping(Integer id) {
        return this.id2PoolKey.remove(id);
    }

    /**
     * Set attribute key=value.
     *
     * @param key
     * @param value
     */
    public void setAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    /**
     * set attribute if key absent.
     *
     * @param key
     * @param value
     * @return
     */
    public Object setAttributeIfAbsent(String key, Object value) {
        return attributes.putIfAbsent(key, value);
    }

    /**
     * Remove attribute.
     *
     * @param key
     */
    public void removeAttribute(String key) {
        attributes.remove(key);
    }

    /**
     * Get attribute.
     *
     * @param key
     * @return
     */
    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    /**
     * Clear attribute.
     */
    public void clearAttributes() {
        attributes.clear();
    }

    /**
     * Getter method for property <tt>invokeFutureMap</tt>.
     *
     * @return property value of invokeFutureMap
     */
    public ConcurrentHashMap<Integer, InvokeFuture> getInvokeFutureMap() {
        return invokeFutureMap;
    }


    public boolean isFine() {
        return this.channel != null && this.channel.isActive();
    }
}
