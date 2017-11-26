package sample.spring.chapter16.domain;

public class FixedDepositDetails {
	private long id; // -- id value is set by the system

	private String customerId;

	private String depositAmount;

	private String tenure;

	private String email;

	public FixedDepositDetails() {
	}

	public FixedDepositDetails(long id, String customerId,
			String depositAmount, String tenure, String email) {
		this.id = id;
		this.customerId = customerId;
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(String depositAmount) {
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

	@Override
	public String toString() {
		return "FixedDepositDetails [id=" + id + ", customerId=" + customerId
				+ ", depositAmount=" + depositAmount + ", tenure=" + tenure
				+ ", email=" + email + "]";
	}
}
