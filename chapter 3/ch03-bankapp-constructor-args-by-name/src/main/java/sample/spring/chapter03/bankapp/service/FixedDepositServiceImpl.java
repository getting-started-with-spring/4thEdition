package sample.spring.chapter03.bankapp.service;

import java.beans.ConstructorProperties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sample.spring.chapter03.bankapp.base.EmailMessageSender;
import sample.spring.chapter03.bankapp.base.JmsMessageSender;
import sample.spring.chapter03.bankapp.base.ServiceTemplate;
import sample.spring.chapter03.bankapp.base.WebServiceInvoker;
import sample.spring.chapter03.bankapp.dao.FixedDepositDao;
import sample.spring.chapter03.bankapp.domain.FixedDepositDetails;

public class FixedDepositServiceImpl extends ServiceTemplate implements FixedDepositService {
	private static Logger logger = LogManager
			.getLogger(FixedDepositServiceImpl.class);

	@ConstructorProperties({"jmsMessageSender","emailMessageSender","webServiceInvoker"})
	public FixedDepositServiceImpl(JmsMessageSender jmsMessageSender,
			EmailMessageSender emailMessageSender,
			WebServiceInvoker webServiceInvoker) {
		super(jmsMessageSender, emailMessageSender, webServiceInvoker);
	}

	private FixedDepositDao fixedDepositDao;

	public FixedDepositDao getFixedDepositDao() {
		return fixedDepositDao;
	}

	public void setFixedDepositDao(FixedDepositDao fixedDepositDao) {
		logger.info("Setting fixedDepositDao property");
		this.fixedDepositDao = fixedDepositDao;
	}

	public FixedDepositDetails getFixedDepositDetails(long id) {
		return fixedDepositDao.getFixedDepositDetails(id);
	}

	public boolean createFixedDeposit(FixedDepositDetails fdd) {
		return fixedDepositDao.createFixedDeposit(fdd);
	}
}
