package sample.spring.chapter16.dao;

import java.util.List;

import sample.spring.chapter16.domain.FixedDepositDetails;

public interface FixedDepositDao {
	List<FixedDepositDetails> getFixedDeposits(String user);

	List<FixedDepositDetails> getAllFixedDeposits();

	void saveFixedDeposit(FixedDepositDetails fixedDepositDetails);

	void closeFixedDeposit(int fixedDepositId);

	FixedDepositDetails getFixedDeposit(int fixedDepositId);

	void editFixedDeposit(FixedDepositDetails fixedDepositDetails);
}
