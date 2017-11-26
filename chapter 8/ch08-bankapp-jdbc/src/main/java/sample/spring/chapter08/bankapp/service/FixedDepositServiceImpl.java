package sample.spring.chapter08.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.spring.chapter08.bankapp.dao.BankAccountDao;
import sample.spring.chapter08.bankapp.dao.FixedDepositDao;
import sample.spring.chapter08.bankapp.domain.FixedDepositDetails;

@Service(value = "fixedDepositService")
public class FixedDepositServiceImpl implements FixedDepositService {

	@Autowired
	@Qualifier(value = "fixedDepositDao")
	private FixedDepositDao myFixedDepositDao;

	@Autowired
	private BankAccountDao bankAccountDao;
	
	@Override
	@Transactional
	public int createFixedDeposit(FixedDepositDetails fdd) throws Exception {
		// -- create fixed deposit
		bankAccountDao.subtractFromAccount(fdd.getBankAccountId(), fdd.getFdAmount());
		return myFixedDepositDao.createFixedDeposit(fdd);
	}

	@Override
	public FixedDepositDetails getFixedDeposit(int fixedDepositId) {
		return myFixedDepositDao.getFixedDeposit(fixedDepositId);
	}
}
