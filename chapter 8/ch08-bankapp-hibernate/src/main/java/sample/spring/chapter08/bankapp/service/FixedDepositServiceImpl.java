package sample.spring.chapter08.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.spring.chapter08.bankapp.dao.BankAccountDao;
import sample.spring.chapter08.bankapp.dao.FixedDepositDao;
import sample.spring.chapter08.bankapp.domain.FixedDepositDetails;

@Service(value = "fixedDepositService")
public class FixedDepositServiceImpl implements FixedDepositService {

	@Autowired
	private FixedDepositDao myFixedDepositDao;

	@Autowired
	private BankAccountDao bankAccountDao;

	@Override
	@Transactional
	public int createFixedDeposit(FixedDepositDetails fdd) throws Exception {
		// -- create fixed deposit
		bankAccountDao.subtractFromAccount(fdd.getBankAccountId()
				.getAccountId(), fdd.getFdAmount());
		return myFixedDepositDao.createFixedDeposit(fdd);
	}

	@Override
	@Transactional
	public FixedDepositDetails getFixedDeposit(int fixedDepositId) {
		return myFixedDepositDao.getFixedDeposit(fixedDepositId);
	}
}
