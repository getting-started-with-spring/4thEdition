package sample.streams;

import java.util.ArrayList;
import java.util.List;

public class SumOfStudentsAges {
	public static void main(String args[]) {
		List<Student> students = new ArrayList<>();
		students.add(new Student("A", 15));
		students.add(new Student("B", 12));
		students.add(new Student("C", 13));

		int sumOfAges = students.stream()
				.mapToInt(s -> s.getAge())
				.sum();
		System.out.println("sum of ages is: " + sumOfAges);
	}
}
