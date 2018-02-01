package org.lyncc.bazinga.rx.bazinga.jdk8.stream;

import static java.util.stream.Collectors.*;
import java.util.*;
import java.util.function.*;
/**
 * stream处理
 *
 * @author liguolin
 * @create 2018-01-30 13:57
 **/
public class Summarizing {

    public static void main(String ... args) {
        System.out.println("Nr. of dishes: " + howManyDishes());
        System.out.println("The most caloric dish is: " + findMostCaloricDish());
        System.out.println("The most caloric dish is: " + findMostCaloricDishUsingComparator());
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Average calories in menu: " + calculateAverageCalories());
        System.out.println("Menu statistics: " + calculateMenuStatistics());
        System.out.println("Short menu: " + getShortMenu());
        System.out.println("Short menu comma separated: " + getShortMenuCommaSeparated());
    }


    private static long howManyDishes() {
        return Dish.menu.stream().collect(counting());
    }

    private static Dish findMostCaloricDish() {
        return Dish.menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
    }

    private static Dish findMostCaloricDishUsingComparator() {
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(dishCaloriesComparator);
        return Dish.menu.stream().collect(reducing(moreCaloricOf)).get();
    }

    private static int calculateTotalCalories() {
        return Dish.menu.stream().collect(summingInt(Dish::getCalories));
    }

    private static Double calculateAverageCalories() {
        return Dish.menu.stream().collect(averagingInt(Dish::getCalories));
    }

    private static IntSummaryStatistics calculateMenuStatistics() {
        return Dish.menu.stream().collect(summarizingInt(Dish::getCalories));
    }

    private static String getShortMenu() {
        return Dish.menu.stream().map(Dish::getName).collect(joining());
    }

    private static String getShortMenuCommaSeparated() {
        return Dish.menu.stream().map(Dish::getName).collect(joining(", "));
    }
}
