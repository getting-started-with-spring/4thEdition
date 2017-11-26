package sample.spring.chapter15.domain;

import java.util.List;

public class FixedDeposits {
	private List<FixedDepositDetails> fdList;

	public List<FixedDepositDetails> getFdList() {
		return fdList;
	}

	public void setFdList(List<FixedDepositDetails> fdList) {
		this.fdList = fdList;
	}
}
