package sample.spring.chapter03.beans;

import java.beans.ConstructorProperties;
import java.util.Date;

public class BankStatement {
	private Date transactionDate;
	private double amount;
	private String transactionType;
	private String referenceNumber;

	@ConstructorProperties({ "transactionDate", "amount", "transactionType",
			"referenceNumber" })
	public BankStatement(Date transactionDate, double amount,
			String transactionType, String referenceNumber) {
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.transactionType = transactionType;
		this.referenceNumber = referenceNumber;
	}

	@Override
	public String toString() {
		return "BankStatement [transactionDate=" + transactionDate
				+ ", amount=" + amount + ", transactionType=" + transactionType
				+ ", referenceNumber=" + referenceNumber + "]";
	}
}