package sample.higherorder;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class ConcatAndHashSimplified {
	private static BiFunction<String, String, String> concatFn = (prefix,
			suffix) -> prefix + " " + suffix;

	private static Function<String, Integer> hashFn = input -> input.hashCode();

	private static Consumer<Object> printFn = input -> System.out
			.println(input);

	public static void main(String args[]) {
		//-- function composition
		BiFunction<String, String, Integer> concatAndHash = concatFn
				.andThen(hashFn);
		printFn.accept(concatAndHash.apply("Welcome", "Java 8"));
	}
}
