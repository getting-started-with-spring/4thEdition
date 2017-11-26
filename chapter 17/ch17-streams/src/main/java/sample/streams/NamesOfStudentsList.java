package sample.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NamesOfStudentsList {
	public static void main(String args[]) {
		List<Student> students = new ArrayList<>();
		students.add(new Student("A", 15));
		students.add(new Student("B", 12));
		students.add(new Student("C", 13));

		List<String> names = students.stream()
				.map(s -> s.getName())
				.collect(Collectors.toList());
		
		names.stream().forEach(name -> System.out.println("Name is: " + name));
	}
}
