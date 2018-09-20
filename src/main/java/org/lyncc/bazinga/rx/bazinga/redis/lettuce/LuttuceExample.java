package org.lyncc.bazinga.rx.bazinga.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * @author liguolin
 * @create 2018-05-30 10:38
 **/
public class LuttuceExample {

    public static void main(String[] args) {

        RedisClient redisClient = RedisClient.create("redis://bazingaLyncc@47.98.164.130:6379/0");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();

        syncCommands.set("key", "Hello, Redis!");

//        RedisFuture<String> future = syncCommands.get("key");

        connection.close();
        redisClient.shutdown();
    }


}
