package org.lyncc.bazinga.rx.bazinga.msentinel.node;


import org.lyncc.bazinga.rx.bazinga.msentinel.node.metric.MetricNode;

import java.util.Map;

public interface Node {

    /**
     * Get incoming request per minute. {@code pass + block}
     */
    long totalRequest();

    /**
     *
     * @return Outgoing request per minute.
     */
    long totalSuccess();

    /**
     * Get block request count per minute.
     */
    long blockRequest();

    /**
     * Get exception count per minute.
     */
    long totalException();

    /**
     * Get pass request per second.
     */
    long passQps();

    /**
     * Get block request per second.
     */
    long blockQps();

    /**
     * Get {@link #passQps()} + {@link #blockQps()} request per second.
     */
    long totalQps();

    /**
     */
    long successQps();

    /**
     * Get estimated max success QPS till now.
     *
     * @return max success QPS
     */
    long maxSuccessQps();

    /**
     * Get exception count per second.
     */
    long exceptionQps();

    /**
     * Get average rt per second.
     *
     * @return average response time per second
     */
    long avgRt();

    /**
     * Get minimal response time.
     *
     * @return recorded minimal response time
     */
    long minRt();

    /**
     * Get current active thread count.
     */
    int curThreadNum();

    /**
     * Get last second block QPS.
     */
    long previousBlockQps();

    /**
     * Last window QPS.
     */
    long previousPassQps();

    /**
     * Fetch all valid metric nodes of resources.
     *
     * @return valid metric nodes of resources
     */
    //TODO
    Map<Long, MetricNode> metrics();

    /**
     * Add pass count.
     */
    void addPassRequest();

    /**
     * Add rt and success count.
     *
     * @param rt response time
     */
    void rt(long rt);

    /**
     * Increase the block count.
     */
    void increaseBlockQps();

    /**
     * Increase the biz exception count.
     */
    void increaseExceptionQps();

    /**
     * Increase current thread count.
     */
    void increaseThreadNum();

    /**
     * Decrease current thread count.
     */
    void decreaseThreadNum();

    /**
     */
    void reset();

    /**
     * Debug only.
     */
    void debug();
}
