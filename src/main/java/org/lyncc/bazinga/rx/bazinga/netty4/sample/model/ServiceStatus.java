package org.lyncc.bazinga.rx.bazinga.netty4.sample.model;

import java.io.Serializable;
import java.util.concurrent.atomic.LongAdder;

public class ServiceStatus implements Serializable {

    private String appId;
    private String serviceName;
    private String address;
    private int    weight;
    private String elasticIp;
    private String registry;
    private String version;

    private long invokeCount;
    private long successCount;
    private long errorCount;
    private long timeoutCount;
    private long clientCount;

    private LongAdder invokeAdder  = new LongAdder();
    private LongAdder successAdder = new LongAdder();
    private LongAdder errorAdder   = new LongAdder();
    private LongAdder timeoutAdder = new LongAdder();
    private LongAdder clientAdder  = new LongAdder();

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getElasticIp() {
        return elasticIp;
    }

    public void setElasticIp(String elasticIp) {
        this.elasticIp = elasticIp;
    }

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getInvokeCount() {
        return invokeCount;
    }

    public void setInvokeCount(long invokeCount) {
        this.invokeCount = invokeCount;
    }

    public long getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(long successCount) {
        this.successCount = successCount;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }

    public long getTimeoutCount() {
        return timeoutCount;
    }

    public void setTimeoutCount(long timeoutCount) {
        this.timeoutCount = timeoutCount;
    }

    public long getClientCount() {
        return clientCount;
    }

    public void setClientCount(long clientCount) {
        this.clientCount = clientCount;
    }

    public LongAdder getInvokeAdder() {
        return invokeAdder;
    }

    public void setInvokeAdder(LongAdder invokeAdder) {
        this.invokeAdder = invokeAdder;
    }

    public LongAdder getSuccessAdder() {
        return successAdder;
    }

    public void setSuccessAdder(LongAdder successAdder) {
        this.successAdder = successAdder;
    }

    public LongAdder getErrorAdder() {
        return errorAdder;
    }

    public void setErrorAdder(LongAdder errorAdder) {
        this.errorAdder = errorAdder;
    }

    public LongAdder getTimeoutAdder() {
        return timeoutAdder;
    }

    public void setTimeoutAdder(LongAdder timeoutAdder) {
        this.timeoutAdder = timeoutAdder;
    }

    public LongAdder getClientAdder() {
        return clientAdder;
    }

    public void setClientAdder(LongAdder clientAdder) {
        this.clientAdder = clientAdder;
    }

    public void addSuccess() {
        invokeAdder.add(1);
        successAdder.add(1);
        invokeCount = invokeAdder.longValue();
        successCount = successAdder.longValue();
    }

    public void addError() {
        invokeAdder.add(1);
        errorAdder.add(1);
        invokeCount = invokeAdder.longValue();
        errorCount = errorAdder.longValue();
    }

    public void addTimeout() {
        invokeAdder.add(1);
        timeoutAdder.add(1);
        invokeCount = invokeAdder.longValue();
        timeoutCount = timeoutAdder.longValue();
    }

    public void addClient() {
        clientAdder.add(1);
        clientCount = clientAdder.longValue();
    }

    public void removeClient() {
        clientAdder.add(-1);
        clientCount = clientAdder.longValue();
    }

}