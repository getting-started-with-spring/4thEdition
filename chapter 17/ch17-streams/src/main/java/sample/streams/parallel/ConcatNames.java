package sample.streams.parallel;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class ConcatNames {
	private static BinaryOperator<String> concatFn =   (combinedNamesStr, name) -> combinedNamesStr
			.concat(name);

	public static void main(String args[]) {
		List<String> names = Arrays.asList("James", "John", "Robert",
				"Michael", "Mary");

		String sequentialStream = names.stream()
				.reduce("", concatFn);
		System.out.println("Serial concat : " + sequentialStream);

		String parallelStream = names.parallelStream()
				.reduce("", concatFn);
		System.out.println("Parallel concat : " + parallelStream);
	}
}