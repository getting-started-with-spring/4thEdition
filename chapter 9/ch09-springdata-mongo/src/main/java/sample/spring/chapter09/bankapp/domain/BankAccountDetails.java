package sample.spring.chapter09.bankapp.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bankaccounts")
public class BankAccountDetails {
	@Id
	private String accountId;

	private int balance;

	private Date lastTransactionTimestamp;

	private List<FixedDepositDetails> fixedDeposits;

	public BankAccountDetails() {
		fixedDeposits = new ArrayList<>();
	}

	public void addFixedDeposit(FixedDepositDetails fixedDeposit) {
		fixedDeposits.add(fixedDeposit);
	}

	public List<FixedDepositDetails> getFixedDeposits() {
		return fixedDeposits;
	}

	public String getAccountId() {
		return accountId;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Date getLastTransactionTimestamp() {
		return lastTransactionTimestamp;
	}

	public void setLastTransactionTimestamp(Date lastTransactionTimestamp) {
		this.lastTransactionTimestamp = lastTransactionTimestamp;
	}

	@Override
	public boolean equals(Object otherObject) {
		BankAccountDetails otherBankAccountDetails = (BankAccountDetails) otherObject;
		if (otherBankAccountDetails.getAccountId() == this.accountId) {
			return true;
		} else {
			return false;
		}
	}
}
