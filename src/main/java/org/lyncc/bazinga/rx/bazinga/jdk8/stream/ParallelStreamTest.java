package org.lyncc.bazinga.rx.bazinga.jdk8.stream;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 并行stream测试
 *
 * @author liguolin
 * @create 2018-02-04 14:38
 **/
public class ParallelStreamTest {

    public static void main(String[] args) {


        long beginTime = System.currentTimeMillis();

        List<String> results = Lists.newArrayList();

        List<Integer> lists = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        results = lists.parallelStream().map((Integer index) ->changeToString(index)).collect(toList());

//        for(Integer index : lists){
//
//            String result = changeToString(index);
//            results.add(result);
//        }

        System.out.println(String.format("cost time %s ms",System.currentTimeMillis()-beginTime));
        results.stream().forEach(System.out::println);
    }

    private static String changeToString(Integer index) {
        try {
            Thread.sleep(500l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello" + index;
    }
}
