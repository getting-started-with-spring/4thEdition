package sample.spring.chapter15.domain;

import sample.spring.chapter15.formatter.AmountFormat;


public class FixedDepositDetails {
	private long id;

	@AmountFormat
	private long depositAmount;
	@AmountFormat
	private String tenure;

	private String email;

	public FixedDepositDetails() {
	}

	public FixedDepositDetails(long id, long depositAmount, String tenure,
			String email) {
		this.id = id;
		this.depositAmount = depositAmount;
		this.tenure = tenure;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(long depositAmount) {
		this.depositAmount = depositAmount;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		return "id :" + id + ", deposit amount : " + depositAmount
				+ ", tenure : " + tenure + ", email : " + email;
	}
}
