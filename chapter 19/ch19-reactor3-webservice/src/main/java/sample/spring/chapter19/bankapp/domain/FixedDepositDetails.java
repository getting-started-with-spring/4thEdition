package sample.spring.chapter19.bankapp.domain;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class FixedDepositDetails {
	@Id
	private ObjectId fixedDepositId;

	private Date fdCreationDate;

	private int fdAmount;

	private int tenure;

	private String active;

	public FixedDepositDetails() {
		this.fixedDepositId = ObjectId.get();
	}

	public ObjectId getFixedDepositId() {
		return fixedDepositId;
	}

	public void setFixedDepositId(ObjectId fixedDepositId) {
		this.fixedDepositId = fixedDepositId;
	}

	public Date getFdCreationDate() {
		return fdCreationDate;
	}

	public void setFdCreationDate(Date fdCreationDate) {
		this.fdCreationDate = fdCreationDate;
	}

	public int getFdAmount() {
		return fdAmount;
	}

	public void setFdAmount(int fdAmount) {
		this.fdAmount = fdAmount;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
}