package sample.streams;

public class Person {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		System.out.println("getName method --> age of " + name + " is " + age);
		return name;
	}

	public int getAge() {
		System.out.println("getAge method --> age of " + name + " is " + age);
		return age;
	}
}
