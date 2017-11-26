package com.sample.functionalstyle.domain;

import org.springframework.beans.factory.annotation.Autowired;

public class Person {
	private Profile profile;

	@Autowired
	public Person(Profile profile) {
		this.profile = profile;
	}

	public Profile getProfile() {
		return profile;
	}
}
