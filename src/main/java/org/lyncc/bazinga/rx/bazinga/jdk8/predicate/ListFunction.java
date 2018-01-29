package org.lyncc.bazinga.rx.bazinga.jdk8.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListFunction {

	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> results = new ArrayList<T>();
		for (T s : list) {
			if (p.test(s)) {
				results.add(s);
			}
		}
		return results;
	}

	public static <T> void consumer(List<T> list, Consumer<T> c) {
		for (T s : list) {
			c.accept(s);
		}
	}

	public static <T, R> List<R> mapping(List<T> list, Function<T, R> f) {
		List<R> result = new ArrayList<>();
		for (T s : list) {
			result.add(f.apply(s));
		}
		return result;
	}

}
