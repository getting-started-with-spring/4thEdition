package sample.streams.parallel;

import java.util.Arrays;
import java.util.List;

public class PrintNames {

	public static void main(String args[]) {
		List<String> names = Arrays.asList("James", "John", "Robert",
				"Michael", "Mary");

		System.out.println("Serial stream result:");
		names.stream().forEach(e -> System.out.println(">" + e));

		System.out.println("\nParallel stream result:");
		names.parallelStream().forEach(e -> System.out.println(">" + e));
	}
}
