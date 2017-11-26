package sample.higherorder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListExample {
	public static void main(String args[]) {
		List<Integer> ints = Arrays.asList(10, 20, 5, 4, 1, 100);
		List<Integer> newInts = new ArrayList<Integer>();
		
		// --given a list - double the odd numbers and add 5 to odd number
		ints.forEach(item -> {
			if (item % 2 == 0) {
				item = item + 5;
			} else {
				item = item * 2;
			}
			newInts.add(item);
		});
		System.out.println(newInts);
	}
}
