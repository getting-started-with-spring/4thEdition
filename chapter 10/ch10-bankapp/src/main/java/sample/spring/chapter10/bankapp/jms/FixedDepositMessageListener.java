package sample.spring.chapter10.bankapp.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import sample.spring.chapter10.bankapp.dao.BankAccountDao;
import sample.spring.chapter10.bankapp.dao.FixedDepositDao;
import sample.spring.chapter10.bankapp.domain.FixedDepositDetails;

public class FixedDepositMessageListener implements MessageListener {
	private static Logger logger = LogManager.getLogger(FixedDepositMessageListener.class);
	
	@Autowired
	@Qualifier(value = "fixedDepositDao")
	private FixedDepositDao myFixedDepositDao;

	@Autowired
	private BankAccountDao bankAccountDao;

	@Transactional(transactionManager="dbTxManager")
	public int createFixedDeposit(FixedDepositDetails fdd) {
		// -- create fixed deposit
		bankAccountDao.subtractFromAccount(fdd.getBankAccountId(),
				fdd.getFdAmount());
		return myFixedDepositDao.createFixedDeposit(fdd);
	}

	@Override
	public void onMessage(Message message) {
		logger.info("FixedDepositMessageListener's onMessage() invoked");
		ObjectMessage objectMessage = (ObjectMessage) message;
		FixedDepositDetails fdd = null;
		try {
			fdd = (FixedDepositDetails) objectMessage.getObject();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		if (fdd != null) {
			createFixedDeposit(fdd);
		}
	}
}
