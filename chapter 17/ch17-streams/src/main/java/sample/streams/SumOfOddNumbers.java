package sample.streams;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SumOfOddNumbers {
	public static void main(String args[]) {
		int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		 
		IntStream intStream = Arrays.stream(numbers);
		int sum = intStream
				.filter(n -> n % 2 != 0)
				.sum();
		System.out.println("Sum of odd numbers in numbers array is : " + sum);
	}
}
