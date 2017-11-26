package sample.spring.chapter03.bankapp.service;

import sample.spring.chapter03.bankapp.dao.FixedDepositDao;
import sample.spring.chapter03.bankapp.domain.FixedDepositDetails;

public interface FixedDepositService {
	FixedDepositDao getFixedDepositDao();

	FixedDepositDetails getFixedDepositDetails(long id);

	boolean createFixedDeposit(FixedDepositDetails fdd);

}
