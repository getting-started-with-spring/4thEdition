package sample.spring.chapter07.bankapp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sample.spring.chapter07.bankapp.dao.FixedDepositDao;
import sample.spring.chapter07.bankapp.domain.FixedDepositDetails;

public class FixedDepositServiceImpl implements FixedDepositService {
	private static Logger logger = LogManager.getLogger(FixedDepositServiceImpl.class);

	private FixedDepositDao fixedDepositDao;

	public FixedDepositServiceImpl() {
		logger.info("initializing");
	}

	public void setFixedDepositDao(FixedDepositDao fixedDepositDao) {
		this.fixedDepositDao = fixedDepositDao;
	}

	public FixedDepositDetails getFixedDepositDetails(long id) {
		return fixedDepositDao.getFixedDepositDetails(id);
	}

	public boolean createFixedDeposit(FixedDepositDetails fdd) {
		return fixedDepositDao.createFixedDeposit(fdd);
	}
}
