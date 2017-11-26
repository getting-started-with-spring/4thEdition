package sample.spring.chapter06.newfeatures.domain;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

public class Profile {
	private List<@Size(min = 5) String> friendNames;

	private Optional<@Length(min = 10, max = 10) String> phoneNumber;
	
	@PositiveOrZero
	private Integer income;

	@Positive
	private Integer age;

	public Profile(List<String> friendNames, Optional<String> phoneNumber, Integer income, Integer age) {
		super();
		this.friendNames = friendNames;
		this.phoneNumber = phoneNumber;
		this.income = income;
		this.age = age;
	}

	public List<String> getFriendNames() {
		return friendNames;
	}

	public void setFriendNames(List<String> friendNames) {
		this.friendNames = friendNames;
	}

	public Optional<String> getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Optional<String> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
