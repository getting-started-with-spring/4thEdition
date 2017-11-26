package sample.spring.chapter10.bankapp.service;

import java.util.List;

import sample.spring.chapter10.bankapp.domain.FixedDepositDetails;


public interface FixedDepositService {
	void createFixedDeposit(FixedDepositDetails fdd) throws Exception;
	FixedDepositDetails getFixedDeposit(int fixedDepositId);
	List<FixedDepositDetails> findFixedDepositsByBankAccount(int bankAccountId);
	FixedDepositDetails getFixedDepositFromCache(int fixedDepositId);
}
