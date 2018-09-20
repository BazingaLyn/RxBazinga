package org.lyncc.bazinga.rx.bazinga.delayqueue.model;

import com.alibaba.fastjson.JSON;

/**
 * @author liguolin
 * @create 2018-08-27 17:38
 **/
public class DelayQueueJob {

    private long id;

    /**
     * 任务类型（具体业务类型）
     */
    private String topic;

    /**
     * 任务的执行时间
     */
    private long delayTime;

    /**
     * 任务的执行超时时间
     */
    private long ttrTime;

    /**
     * 任务具体的消息内容，用于处理具体业务逻辑用
     */
    private String message;

    /**
     * 任务重试次数
     */
    private int retryCount = 0;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    public long getTtrTime() {
        return ttrTime;
    }

    public void setTtrTime(long ttrTime) {
        this.ttrTime = ttrTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public static final class DelayQueueJobBuilder {
        private long id;
        private String topic;
        private long delayTime;
        private long ttrTime;
        private String message;

        public DelayQueueJobBuilder() {
        }

        public static DelayQueueJobBuilder aDelayQueueJob() {
            return new DelayQueueJobBuilder();
        }

        public DelayQueueJobBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public DelayQueueJobBuilder withTopic(String topic) {
            this.topic = topic;
            return this;
        }

        public DelayQueueJobBuilder withDelayTime(long delayTime) {
            this.delayTime = delayTime;
            return this;
        }

        public DelayQueueJobBuilder withTtrTime(long ttrTime) {
            this.ttrTime = ttrTime;
            return this;
        }

        public DelayQueueJobBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public DelayQueueJob build() {
            DelayQueueJob delayQueueJob = new DelayQueueJob();
            delayQueueJob.setId(id);
            delayQueueJob.setTopic(topic);
            delayQueueJob.setDelayTime(delayTime);
            delayQueueJob.setTtrTime(ttrTime);
            delayQueueJob.setMessage(message);
            return delayQueueJob;
        }
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
