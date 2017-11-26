package sample.spring.chapter13.domain;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class FixedDepositDetails {
	private long id;

	@Min(1000)
	@Max(500000)
	private long depositAmount;

	@Email
	@Size(min = 10, max = 25)
	private String email;

	@NotNull
	private Date maturityDate;

	public FixedDepositDetails() {
	}

	public FixedDepositDetails(long id, long depositAmount, Date maturityDate,
			String email) {
		this.id = id;
		this.depositAmount = depositAmount;
		this.maturityDate = maturityDate;
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
		System.out.println("depositAmount - setter called");
		this.depositAmount = depositAmount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String toString() {
		return "id :" + id + ", deposit amount : " + depositAmount
				+ ", email : " + email;
	}
}
