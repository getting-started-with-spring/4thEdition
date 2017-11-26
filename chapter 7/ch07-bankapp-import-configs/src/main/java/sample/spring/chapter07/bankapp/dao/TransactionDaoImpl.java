package sample.spring.chapter07.bankapp.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransactionDaoImpl implements TransactionDao {
	private static Logger logger = LogManager.getLogger(TransactionDaoImpl.class);
	
	@Override
	public void getTransactions(String customerId) {
		logger.info("Getting transactions");
	}
}
