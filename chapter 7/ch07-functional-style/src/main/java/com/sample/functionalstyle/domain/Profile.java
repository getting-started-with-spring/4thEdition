package com.sample.functionalstyle.domain;

public class Profile {
	private String name;
	private String age;

	public Profile() {
		name = "defaultName";
		age = "99";
	}
	
	public Profile(String name, String age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
