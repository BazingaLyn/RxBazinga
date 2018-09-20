package org.lyncc.bazinga.rx.bazinga.redis.jedis;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import javafx.scene.chart.ValueAxis;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.*;
import java.util.function.Supplier;

/**
 * @author liguolin
 * @create 2018-07-31 17:52
 **/
public class JedisBaseOperationTest {

    private static final Logger logger = LoggerFactory.getLogger(JedisBaseOperationTest.class);


    public Jedis jedis;

    @Before
    public void before(){

        JedisPool jedisPool = new JedisPool(new GenericObjectPoolConfig(),"47.98.164.130",6379);

        jedis = new Jedis("47.98.164.130",6379);
        jedis.auth("qazwsxedc");
        logger.info(jedis.ping());
    }



    @Test
    public void test10(){
//        System.out.println(jedis.zrangeWithScores("delayBucket::192168200114::0",0,0));
        jedis.lpush("redisReadyQueue::192168200114::topic1","2222");
        jedis.lpush("redisReadyQueue::192168200114::topic1","3333");
        List<String> lists = jedis.brpop(5,"redisReadyQueue::192168200114::topic1");
        List<String> lists3 = jedis.brpop(5,"redisReadyQueue::192168200114::topic1");
        System.out.println(lists);
        System.out.println(lists3);
//        jedis.lpush("redisReadyQueue::192168200114::topic1","2222");
//        String lists2 = jedis.rpop("redisReadyQueue::192168200114::topic1");
//
//        System.out.println(lists2);
    }


    @Test
    public void test1() throws InterruptedException {

        //String
        String result = jedis.set("hello","redis");
        System.out.println(result);
        System.out.println(jedis.get("hello"));
        jedis.mset("bye","jedis","noon","lyncc");
        jedis.mget("hello","bye","noon").stream().forEach(System.out::println);

        jedis.setex("love",5,"smile");

        for (int i = 0;i < 6 ; i++){
            Thread.sleep(1000L);
            System.out.println(jedis.get("love"));
            // 不存在就插入，插入成功返回1
            System.out.println(jedis.setnx("love","cry"));
        }

        jedis.set("number","1");
        jedis.incrBy("number",2);
        System.out.println(jedis.get("number"));


    }



    @Test
    public void testList(){
        // list
        Long count = jedis.lpush("smile::list","lyncc","fly100%","Th000","Th000");
        System.out.println(count);
        long beginTime = System.currentTimeMillis();
        jedis.brpop(1,"smile::list").stream().forEach(System.out::println);
        logger.info("brpop cost time {}ms",System.currentTimeMillis() - beginTime);
        jedis.brpop(1,"smile::list").stream().forEach(System.out::println);
//        while(true){
//            long beginTime1 = System.currentTimeMillis();
//            List<String> result = Optional.ofNullable(jedis.brpop(1,"smile::list")).orElseThrow(() -> new IllegalArgumentException("用户没有找到"));
//            logger.info("brpop cost time {}ms",System.currentTimeMillis() - beginTime1);
//            result.stream().forEach(System.out::println);
//        }

        jedis.lpush("smile::list::time","lyncc","fly100%","Th000","Th000");
        System.out.println(jedis.lindex("smile::list::time",2));
    }


    @Test
    public void testhash(){
        jedis.hset("hello::set","setField1","hello");

    }

    @Test
    public void testBit(){
        jedis.setbit("hello::bit",0,true);

        System.out.println(jedis.getbit("hello::bit",0));
        System.out.println(jedis.getbit("hello::bit",1));
        String value = jedis.get("hello::bit");
        System.out.println(value);
    }



    @Test
    public void testZSet(){

//        List<String> str = null;
//        Optional.ofNullable(str).
//                orElseGet(() -> new ArrayList<>()).
//                stream().forEach(System.out::println);



        Map<String,Double> values = Maps.newHashMap();
        values.put("lyncc",99.9D);
        values.put("fly100%",99D);
        values.put("fly99%",99D);
        values.put("th000",98D);
        values.put("ted",90D);
        values.put("bazingaLyncc",9D);
        values.put("shine",7D);
        values.put("shine2",7D);
        jedis.zadd("hello::value",values);

        Set<String> infos = jedis.zrange("hello::value",0,-1);
        Set<Tuple> infos2 = jedis.zrangeWithScores("hello::value",0,0);
        System.out.println(infos);
        if(null != infos2 && infos.size() > 0){
            List<Tuple> tuples = Lists.newArrayList(infos2);
            Tuple tuple  = tuples.get(0);
            System.out.println(new String(tuple.getBinaryElement())+tuple.getScore());
        }

        jedis.zrem("hello::value","shine");
        infos = jedis.zrange("hello::value",0,-1);


//        Set<String> temp  = jedis.zrangeByScore("hello::value",99D,99D);

        System.out.println(infos);

//        System.out.println(temp);



//        Optional.ofNullable(infos).orElseThrow(() -> {throw new IllegalArgumentException();}).stream().

    }


}
