package sample.spring.chapter05.bankapp.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sample.spring.chapter05.bankapp.common.InstanceValidator;
import sample.spring.chapter05.bankapp.domain.FixedDepositDetails;

public class FixedDepositDaoImpl implements FixedDepositDao, InstanceValidator {
	private static Logger logger = LogManager.getLogger(FixedDepositDaoImpl.class);
	private FixedDepositDetails fixedDepositDetails;
	private DatabaseConnection connection;

	public FixedDepositDaoImpl() {
		logger.info("FixedDepositDaoImpl's constructor invoked");
	}

	public void setFixedDepositDetails(FixedDepositDetails fixedDepositDetails) {
		this.fixedDepositDetails = fixedDepositDetails;
	}

	public void initializeDbConnection() {
		logger.info("FixedDepositDaoImpl's initializeDbConnection method invoked");
		connection = DatabaseConnection.getInstance();
	}

	public boolean createFixedDeposit(long id, float depositAmount, int tenure,
			String email) {
		fixedDepositDetails.setDepositAmount(depositAmount);
		fixedDepositDetails.setEmail(email);
		fixedDepositDetails.setId(id);
		fixedDepositDetails.setTenure(tenure);
		
		logger.info("FixedDepositDaoImpl's createFixedDeposit method invoked");
		// -- save the fixed deposits and then return true
		return true;
	}

	public void releaseDbConnection() {
		logger.info("FixedDepositDaoImpl's releaseDbConnection method invoked");
		connection.releaseConnection();
	}

	@Override
	public void validateInstance() {
		logger.info("Validating FixedDepositDaoImpl instance");
		if (connection == null) {
			logger.error("Failed to obtain DatabaseConnection instance");
		}
	}
}
