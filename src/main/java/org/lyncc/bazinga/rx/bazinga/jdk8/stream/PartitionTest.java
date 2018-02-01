package org.lyncc.bazinga.rx.bazinga.jdk8.stream;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.partitioningBy;

/**
 * 分区测试
 *
 * @author liguolin
 * @create 2018-01-30 14:54
 **/
public class PartitionTest {

    public static void main(String[] args) {

        // 分区
        Map<Boolean,List<Dish>> partitionedMenu = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }
}
