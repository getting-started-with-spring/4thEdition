package sample.spring.chapter03.beans;

import java.util.Currency;
import java.util.Date;

public class BankDetails {
	private String bankName;
	private byte[] bankPrimaryBusiness;
	private char[] headOfficeAddress;
	private char privateBank;
	private Currency primaryCurrency;
	private Date dateOfInception;
	private Address branchAddresses;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public byte[] getBankPrimaryBusiness() {
		return bankPrimaryBusiness;
	}

	public void setBankPrimaryBusiness(byte[] bankPrimaryBusiness) {
		this.bankPrimaryBusiness = bankPrimaryBusiness;
	}

	public char[] getHeadOfficeAddress() {
		return headOfficeAddress;
	}

	public void setHeadOfficeAddress(char[] headOfficeAddress) {
		this.headOfficeAddress = headOfficeAddress;
	}

	public Currency getPrimaryCurrency() {
		return primaryCurrency;
	}

	public void setPrimaryCurrency(Currency primaryCurrency) {
		this.primaryCurrency = primaryCurrency;
	}

	public Date getDateOfInception() {
		return dateOfInception;
	}

	public void setDateOfInception(Date dateOfInception) {
		this.dateOfInception = dateOfInception;
	}

	public char getPrivateBank() {
		return privateBank;
	}

	public void setPrivateBank(char privateBank) {
		this.privateBank = privateBank;
	}

	public Address getBranchAddresses() {
		return branchAddresses;
	}

	public void setBranchAddresses(Address branchAddresses) {
		this.branchAddresses = branchAddresses;
	}

	@Override
	public String toString() {
		return "BankDetails [bankName=" + bankName + ", bankPrimaryBusiness="
				+ new String(bankPrimaryBusiness) + ", headOfficeAddress="
				+ new String(headOfficeAddress) + ", primaryCurrency="
				+ primaryCurrency + ", dateOfInception=" + dateOfInception
				+ ", privateBank=" + privateBank + ", branchAddresses="
				+ branchAddresses + "]";
	}
}
