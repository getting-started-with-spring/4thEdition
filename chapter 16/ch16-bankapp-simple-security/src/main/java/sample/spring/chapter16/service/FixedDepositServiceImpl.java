package sample.spring.chapter16.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.spring.chapter16.dao.FixedDepositDao;
import sample.spring.chapter16.domain.FixedDepositDetails;

@Service
public class FixedDepositServiceImpl implements FixedDepositService {
	@Autowired
	private FixedDepositDao fixedDepositDao;
	
	@Override
	public List<FixedDepositDetails> getFixedDeposits(String user) {
		return fixedDepositDao.getFixedDeposits(user);
	}
	
	@Override
	public List<FixedDepositDetails> getAllFixedDeposits() {
		return fixedDepositDao.getAllFixedDeposits();
	}

	@Override
	public void saveFixedDeposit(FixedDepositDetails fixedDepositDetails) {
		fixedDepositDao.saveFixedDeposit(fixedDepositDetails);
	}

	@Override
	public void closeFixedDeposit(int fixedDepositId) {
		fixedDepositDao.closeFixedDeposit(fixedDepositId);
	}

	@Override
	public FixedDepositDetails getFixedDeposit(int fixedDepositId) {
		return fixedDepositDao.getFixedDeposit(fixedDepositId);
	}
	
	@Override
	public void editFixedDeposit(FixedDepositDetails fixedDepositDetails) {
		fixedDepositDao.editFixedDeposit(fixedDepositDetails);
	}
}
