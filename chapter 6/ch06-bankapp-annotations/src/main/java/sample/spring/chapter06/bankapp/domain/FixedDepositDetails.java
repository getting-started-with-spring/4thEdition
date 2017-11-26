package sample.spring.chapter06.bankapp.domain;

public class FixedDepositDetails {
	private long id;
	private float depositAmount;
	private int tenure;
	private String email;

	public FixedDepositDetails(long id, float depositAmount, int tenure,
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
