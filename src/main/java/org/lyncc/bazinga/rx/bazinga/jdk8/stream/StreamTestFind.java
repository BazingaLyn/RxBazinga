package org.lyncc.bazinga.rx.bazinga.jdk8.stream;

public class StreamTestFind {
	
	public static void main(String[] args) {
		Dish.menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(d -> System.out.println(d.getName()));
	}

}
