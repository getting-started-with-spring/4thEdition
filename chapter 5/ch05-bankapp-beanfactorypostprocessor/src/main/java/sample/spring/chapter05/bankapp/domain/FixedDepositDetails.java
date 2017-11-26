package sample.spring.chapter05.bankapp.domain;

public class FixedDepositDetails {
	private long id;
	private float depositAmount;
	private int tenure;
	private String email;

	public FixedDepositDetails() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(float depositAmount) {
		this.depositAmount = depositAmount;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
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
