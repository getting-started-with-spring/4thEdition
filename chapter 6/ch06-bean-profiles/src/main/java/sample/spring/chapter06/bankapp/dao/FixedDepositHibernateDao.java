package sample.spring.chapter06.bankapp.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import sample.spring.chapter06.bankapp.domain.DataSource;
import sample.spring.chapter06.bankapp.domain.FixedDepositDetails;

@Profile({ "hibernate", "default" })
@Repository
public class FixedDepositHibernateDao implements FixedDepositDao {
	private static Logger logger = LogManager.getLogger(FixedDepositHibernateDao.class);
	
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private Map<Long, FixedDepositDetails> fixedDeposits = new HashMap<Long, FixedDepositDetails>();

	@Autowired
	public FixedDepositHibernateDao(DataSource dataSource) {
		this.dataSource = dataSource;
		logger.info("initializing");
	}

	public FixedDepositDetails getFixedDepositDetails(long id) {
		return fixedDeposits.get(id);
	}

	public boolean createFixedDeposit(FixedDepositDetails fdd) {
		fixedDeposits.put(fdd.getId(), fdd);
		return true;
	}
}
