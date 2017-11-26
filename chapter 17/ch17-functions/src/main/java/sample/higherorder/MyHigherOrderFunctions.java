package sample.higherorder;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class MyHigherOrderFunctions {
	private static Function<String, Function<String, String>> concatFn = prefix -> {
		Function<String, String> addSuffixFn = suffix -> {
			return prefix + " " + suffix;
		};
		return addSuffixFn;
	};

	private static Function<String, Integer> hashFn = input -> input.hashCode();

	private static BiConsumer<String, Function<String, Integer>> hashAndPrintFn = (
			input, hashFn) -> {
		System.out.println(hashFn.apply(input));
	};

	public static void main(String args[]) {
		Function<String, String> suffixFn = concatFn.apply("Welcome");
		String string = suffixFn.apply("Java 8");
		hashAndPrintFn.accept(string, hashFn);
	}
}
