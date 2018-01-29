package org.lyncc.bazinga.rx.bazinga.jdk8.stream;

import static java.util.stream.Collectors.toList;

public class StreamTest {

	public static void main(String[] args) {

		Dish.menu.stream().
						filter((Dish d) -> d.getCalories() > 300).
						map(Dish::getName).
						limit(3).collect(toList()).
						forEach(s->System.out.println(s));

	}

}
