package org.lyncc.bazinga.rx.bazinga.jdk8.stream;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * bifunction 学习
 *
 * @author liguolin
 * @create 2018-01-30 10:59
 **/
public class BiFunctionTest {

    public static void main(String[] args) {

        List<Integer> weights = Arrays.asList(7,5,4,10);

        map(weights,(Integer weight)-> new Apple("red",weight)).forEach(System.out::println);

    }


    public static List<Apple> map(List<Integer> list, Function<Integer,Apple> function){

        return list.stream().map(function).collect(Collectors.toList());

    }



    public static class Apple {

        private String color;

        private Integer weight;

        public Apple(String color, Integer weight) {
            this.color = color;
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
}
