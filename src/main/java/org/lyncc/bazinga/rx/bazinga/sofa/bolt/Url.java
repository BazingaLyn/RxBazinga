package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import org.lyncc.bazinga.rx.bazinga.sofa.bolt.rpc.RpcProtocolV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.SoftReference;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liguolin
 * @create 2018-07-18 16:46
 **/
public class Url {

    /** origin url */
    private String     originUrl;

    /** ip, can be number format or hostname format*/
    private String     ip;

    /** port, should be integer between (0, 65535]*/
    private int        port;

    /** unique key of this url */
    private String     uniqueKey;

    /** URL args: timeout value when do connect */
    private int        connectTimeout;

    /** URL args: protocol */
    private byte       protocol;

    /** URL args: version */
    private byte       version = RpcProtocolV2.PROTOCOL_VERSION_1;

    /** URL agrs: connection number */
    private int        connNum;

    /** URL agrs: whether need warm up connection */
    private boolean    connWarmup;

    /** URL agrs: all parsed args of each originUrl */
    private Properties properties;


    protected Url(String originUrl) {
        this.originUrl = originUrl;
    }

    public Url(String ip, int port) {
        this(ip + RemotingAddressParser.COLON + port);
        this.ip = ip;
        this.port = port;
        this.uniqueKey = this.originUrl;
    }

    public Url(String originUrl, String ip, int port) {
        this(originUrl);
        this.ip = ip;
        this.port = port;
        this.uniqueKey = ip + RemotingAddressParser.COLON + port;
    }

    public Url(String originUrl, String ip, int port, Properties properties) {
        this(originUrl, ip, port);
        this.properties = properties;
    }

    public Url(String originUrl, String ip, int port, String uniqueKey, Properties properties) {
        this(originUrl, ip, port);
        this.uniqueKey = uniqueKey;
        this.properties = properties;
    }

    public String getProperty(String key) {
        if (properties == null) {
            return null;
        }
        return properties.getProperty(key);
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        if (connectTimeout <= 0) {
            throw new IllegalArgumentException("Illegal value of connection number [" + connNum
                    + "], must be a positive integer].");
        }
        this.connectTimeout = connectTimeout;
    }

    public byte getProtocol() {
        return protocol;
    }

    public void setProtocol(byte protocol) {
        this.protocol = protocol;
    }

    public int getConnNum() {
        return connNum;
    }

    public void setConnNum(int connNum) {
        if (connNum <= 0 || connNum > Configs.MAX_CONN_NUM_PER_URL) {
            throw new IllegalArgumentException("Illegal value of connection number [" + connNum
                    + "], must be an integer between ["
                    + Configs.DEFAULT_CONN_NUM_PER_URL + ", "
                    + Configs.MAX_CONN_NUM_PER_URL + "].");
        }
        this.connNum = connNum;
    }

    public boolean isConnWarmup() {
        return connWarmup;
    }

    public void setConnWarmup(boolean connWarmup) {
        this.connWarmup = connWarmup;
    }

    public Properties getProperties() {
        return properties;
    }




    /** Use {@link SoftReference} to cache parsed urls. Key is the original url. */
    public static ConcurrentHashMap<String, SoftReference<Url>> parsedUrls  = new ConcurrentHashMap<String, SoftReference<Url>>();

    /** for unit test only, indicate this object have already been GCed */
    public static volatile boolean                              isCollected = false;

    /** logger */
    private static final Logger logger      = LoggerFactory
            .getLogger("RpcRemoting");

    /**
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        try {
            isCollected = true;
            parsedUrls.remove(this.getOriginUrl());
        } catch (Exception e) {
            logger.error("Exception occurred when do finalize for Url [{}].", this.getOriginUrl(),
                    e);
        }
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

}
