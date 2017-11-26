package sample.spring.chapter06.bankapp.service;

import javax.inject.Inject;
import javax.inject.Named;

import sample.spring.chapter06.bankapp.dao.FixedDepositDao;
import sample.spring.chapter06.bankapp.domain.FixedDepositDetails;

@Named(value="fixedDepositService")
public class FixedDepositServiceImpl implements FixedDepositService {
	
	@Inject
	@Named(value="myFixedDepositDao")
	private FixedDepositDao myFixedDepositDao;
	
	@Override
	public void createFixedDeposit(FixedDepositDetails fdd) throws Exception {
		// -- create fixed deposit
		myFixedDepositDao.createFixedDeposit(fdd);
	}
}
