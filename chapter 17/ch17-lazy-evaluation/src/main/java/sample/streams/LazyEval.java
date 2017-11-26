package sample.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LazyEval {
	public static void main(String args[]) {
		List<Person> persons = new ArrayList<>();

		persons.add(new Person("Tom", 15));
		persons.add(new Person("Sam", 30));
		persons.add(new Person("Bob", 25));
		persons.add(new Person("Sam", 18));
		persons.add(new Person("Tim", 25));

		IntStream ageStream = persons.stream()
			.filter(p -> p.getName().equals("Sam"))
			.mapToInt(p -> p.getAge());
		
		System.out.println("main method --> Calling findFirst operation");
	
		ageStream.findFirst().ifPresent(e -> System.out.println("output --> Sam's age is : " + e));
	}
}
