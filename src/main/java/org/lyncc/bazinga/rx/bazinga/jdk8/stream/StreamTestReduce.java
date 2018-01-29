package org.lyncc.bazinga.rx.bazinga.jdk8.stream;

public class StreamTestReduce {

	public static void main(String[] args) {
		int sum = Dish.menu.stream().mapToInt(Dish::getCalories).reduce(0, (a,b)->(a+b));
		System.out.println(sum);
		
		Dish.menu.stream().mapToInt(Dish::getCalories).reduce(Integer::max);
	}

}
