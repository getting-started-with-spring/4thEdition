package sample.streams;

import java.util.ArrayList;
import java.util.List;

public class ConcatenatedStudentNames {
	public static void main(String args[]) {
		List<Student> students = new ArrayList<>();
		students.add(new Student("A", 15));
		students.add(new Student("B", 12));

		String combinedNames = students
				.stream()
				.map(s -> s.getName())
				.reduce("",
					(combinedNamesStr, name) -> combinedNamesStr.concat(name + ","));

		System.out.println("student names " + combinedNames);
	}
}
