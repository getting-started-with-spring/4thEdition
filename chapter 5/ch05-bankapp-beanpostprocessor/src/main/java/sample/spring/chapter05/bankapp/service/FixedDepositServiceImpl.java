package sample.spring.chapter05.bankapp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sample.spring.chapter05.bankapp.common.DependencyResolver;
import sample.spring.chapter05.bankapp.common.MyApplicationContext;
import sample.spring.chapter05.bankapp.dao.FixedDepositDao;
import sample.spring.chapter05.bankapp.domain.FixedDepositDetails;

public class FixedDepositServiceImpl implements FixedDepositService,
		DependencyResolver {
	private static Logger logger = LogManager
			.getLogger(FixedDepositServiceImpl.class);
	private FixedDepositDao fixedDepositDao;
	
	@Override
	public void createFixedDeposit(FixedDepositDetails fdd) throws Exception {
		// -- create fixed deposit
		fixedDepositDao.createFixedDeposit(fdd);
	}

	@Override
	public void resolveDependency(MyApplicationContext myApplicationContext) {
		logger.info("Resolving dependencies of FixedDepositServiceImpl instance");
		fixedDepositDao = myApplicationContext.getBean(FixedDepositDao.class);
	}
}
