package org.lyncc.bazinga.rx.bazinga.jdk8.stream;

import java.util.Arrays;
import java.util.List;

public class StreamTest2 {
	
	public static final List<Dish> menu =
            Arrays.asList( new Dish("pork", false, 800, Dish.Type.MEAT),
                           new Dish("beef", false, 700, Dish.Type.MEAT),
                           new Dish("chicken", false, 400, Dish.Type.MEAT),
                           new Dish("french fries", true, 530, Dish.Type.OTHER),
                           new Dish("rice", true, 350, Dish.Type.OTHER),
                           new Dish("season fruit", true, 120, Dish.Type.OTHER),
                           new Dish("pizza", true, 550, Dish.Type.OTHER),
                           new Dish("prawns", false, 400, Dish.Type.FISH),
                           new Dish("salmon", false, null, Dish.Type.FISH));

	public static void main(String[] args) {

		System.out.println(menu.stream().mapToInt(Dish::getCalories).filter((each) -> each != 1 ).reduce(0, (a, b)->(a+b)));

	}

}
