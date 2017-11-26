package sample.streams;

import java.util.Arrays;
import java.util.List;

public class SumUsingForLoop {
	public static void main(String args[]) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		int sum = 0;
		for(int n : numbers) {
			if(n % 2 != 0) {
				sum = sum + n;
			}
		}
		System.out.println("Sum of odd numbers in numbers array is : " + sum);
	}
}
