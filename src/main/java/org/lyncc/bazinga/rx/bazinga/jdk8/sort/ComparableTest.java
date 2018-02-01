package org.lyncc.bazinga.rx.bazinga.jdk8.sort;

import org.lyncc.bazinga.rx.bazinga.jdk8.predicate.ListFunctionTest.Values;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;

public class ComparableTest {

	private static List<Values> params = new ArrayList<>();

	static {
		params.add(new Values(1, 2));
		params.add(new Values(31, 4));
		params.add(new Values(5, 6));
		params.add(new Values(7, 8));
		params.add(new Values(91, 10));
		params.add(new Values(11, 12));
	}
	
	public static void main(String[] args) {
		params.sort(comparing(Values::getValue1));
		params.forEach((Values sortValues) -> System.out.println(sortValues));
	}

}
