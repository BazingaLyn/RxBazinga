package org.lyncc.bazinga.rx.bazinga.jdk8.stream;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamListToMap {
	
	public static void main(String[] args) {
		
		//listToMap
		Map<String,Integer> map = Dish.menu.stream().collect(Collectors.toMap(Dish::getName, Dish::getCalories));
		
		//返回对象本身
		Map<String,Dish> map1 = Dish.menu.stream().collect(Collectors.toMap(Dish::getName, Function.identity()));
		
		//重复key报错的问题 后面的key取代前面的key值
		Map<String,Dish> map2 = Dish.menu.stream().collect(Collectors.toMap(Dish::getName, Function.identity(),(key1,key2) -> key2));
		
	}

}
