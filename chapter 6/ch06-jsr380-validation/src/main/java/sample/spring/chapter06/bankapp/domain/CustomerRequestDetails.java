package sample.spring.chapter06.bankapp.domain;

import java.util.Calendar;

public class CustomerRequestDetails {
	private String type;
	private String description;
	private Calendar accountSinceDate;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getAccountSinceDate() {
		return accountSinceDate;
	}

	public void setAccountSinceDate(Calendar accountSinceDate) {
		this.accountSinceDate = accountSinceDate;
	}
}
