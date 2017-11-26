package sample.spring.chapter19.bankapp.domain;

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

	private long count;

	private long findOne;

	public BankAccountDetails() {
		fixedDeposits = new ArrayList<>();
	}

	public long getFindOne() {
		return findOne;
	}

	public void setFindOne(long findOne) {
		this.findOne = findOne;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
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
