package sample.spring.chapter02.bankapp.service;

import sample.spring.chapter02.bankapp.dao.FixedDepositDao;
import sample.spring.chapter02.bankapp.domain.FixedDepositDetails;

public interface FixedDepositService {
	FixedDepositDao getFixedDepositDao();

	FixedDepositDetails getFixedDepositDetails(long id);

	boolean createFixedDeposit(FixedDepositDetails fdd);

}
