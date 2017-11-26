package sample.spring.chapter07.bankapp.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sample.spring.chapter07.bankapp.domain.FixedDepositDetails;

public class FixedDepositDaoImpl implements FixedDepositDao {
	private static Logger logger = LogManager
			.getLogger(FixedDepositDaoImpl.class);
	
	public boolean createFixedDeposit(FixedDepositDetails fdd) {
		// -- save the fixed deposits and then return true
		logger.info("Saving fixed deposit details");
		return true;
	}
}
