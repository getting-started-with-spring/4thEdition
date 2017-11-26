package sample.spring.chapter10.bankapp.jms;

import javax.jms.JMSException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import sample.spring.chapter10.bankapp.dao.BankAccountDao;
import sample.spring.chapter10.bankapp.dao.FixedDepositDao;
import sample.spring.chapter10.bankapp.domain.FixedDepositDetails;

/**
 * This class shows usage of @JmsListener annotation.
 */
@Component
public class MyAnnotatedJmsListener {
	private static Logger logger = LogManager.getLogger(MyAnnotatedJmsListener.class);

	@Autowired
	private transient MailSender mailSender;

	@Autowired
	@Qualifier("requestReceivedTemplate")
	private transient SimpleMailMessage simpleMailMessage;

	@Autowired
	@Qualifier(value = "fixedDepositDao")
	private FixedDepositDao myFixedDepositDao;

	@Autowired
	private BankAccountDao bankAccountDao;

	public void sendEmail() {
		mailSender.send(simpleMailMessage);
	}

	@JmsListener(destination = "emailQueueDestination")
	public void processEmailMessage(Message<String> message) {
		logger.info("processEmailMessage() invoked");
		simpleMailMessage.setTo(message.getPayload());
		sendEmail();
	}

	@JmsListener(destination = "fixedDepositDestination")
	public void processFixedDeposit(Message<FixedDepositDetails> message) throws JMSException {
		logger.info("processFixedDeposit() invoked");
		FixedDepositDetails fdd = message.getPayload();
		if (fdd != null) {
			createFixedDeposit(fdd);
		}
	}

	@Transactional(transactionManager = "dbTxManager")
	public int createFixedDeposit(FixedDepositDetails fdd) {
		// -- create fixed deposit
		bankAccountDao.subtractFromAccount(fdd.getBankAccountId(), fdd.getFdAmount());
		return myFixedDepositDao.createFixedDeposit(fdd);
	}
}
