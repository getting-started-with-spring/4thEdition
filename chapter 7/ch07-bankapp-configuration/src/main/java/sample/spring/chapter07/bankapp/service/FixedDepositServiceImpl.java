package sample.spring.chapter07.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;

import sample.spring.chapter07.bankapp.dao.FixedDepositDao;
import sample.spring.chapter07.bankapp.domain.FixedDepositDetails;

public class FixedDepositServiceImpl implements FixedDepositService {
	
	@Autowired
	private FixedDepositDao fixedDepositDao;
	
	@Override
	public void createFixedDeposit(FixedDepositDetails fdd) throws Exception {
		// -- create fixed deposit
		fixedDepositDao.createFixedDeposit(fdd);
	}
}
