package org.lyncc.bazinga.rx.bazinga.jdk8.stream;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamTestBase {
	
	public static void main(String[] args) {
		
		String[] arrayWords = {"Goodbye","World"};
		Stream<String> streamOfWord = Arrays.stream(arrayWords);
		streamOfWord.collect(toList()).forEach((str) -> System.out.println(str));
		
		streamOfWord.map(word -> word.split("")).map(Arrays::stream).distinct().collect(toList());
		
	}

}
