package org.lyncc.bazinga.rx.bazinga.guava.base;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author liguolin
 * @create 2018-06-19 16:33
 **/
public class BaseTest {

    @Test
    public void test1(){

        Function<Integer, String> function = from -> Integer.toBinaryString(from.intValue());
        Set set = new TreeSet(Arrays.asList(32, 64, 128,2));
        Map<Integer, String> immutableMap = Maps.toMap(set, function);
        System.out.println(JSON.toJSONString(immutableMap));

    }

    @Test
    public void test2(){

        String key = "a-key";
        Multimap<String, String> map = ArrayListMultimap.create();

        map.put(key, "firstValue");
        map.put(key, "secondValue");

        assertEquals(2, map.size());

        Collection<String> values = map.get(key);
        System.out.println(values);

    }


}
