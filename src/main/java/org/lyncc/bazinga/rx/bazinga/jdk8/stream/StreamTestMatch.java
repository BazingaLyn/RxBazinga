package org.lyncc.bazinga.rx.bazinga.jdk8.stream;

public class StreamTestMatch {
	
	public static void main(String[] args) {
		
		Dish.menu.stream().anyMatch(Dish::isVegetarian);
		
		Dish.menu.stream().noneMatch(Dish::isVegetarian);
		
	}

}
