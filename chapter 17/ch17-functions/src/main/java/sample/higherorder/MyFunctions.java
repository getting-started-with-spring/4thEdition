package sample.higherorder;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class MyFunctions {
	private static BiFunction<String, String, String> concatFn = (prefix,
			suffix) -> prefix + " " + suffix;
	private static Function<String, Integer> hashFn = input -> input.hashCode();
	private static Consumer<Object> printFn = input -> System.out
			.println(input);

	public static void main(String args[]) {
		printFn.accept(concatAndHash("Welcome", "Java 8"));
	}

	private static int concatAndHash(String prefix, String suffix) {
		return hashFn.apply(concatFn.apply(prefix, suffix));
	}
}
