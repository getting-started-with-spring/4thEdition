package sample.spring.chapter10.bankapp.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import sample.spring.chapter10.bankapp.dao.BankAccountDao;
import sample.spring.chapter10.bankapp.dao.FixedDepositDao;
import sample.spring.chapter10.bankapp.domain.FixedDepositDetails;

/**
 * This class shows usage of @JmsListener annotation. To use this class, specify
 * <jms:annotation-driven> and modify <listener-container> element (as explained
 * in the book).
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
	public void processEmailMessage(Message message) {
		logger.info("processEmailMessage() invoked");
		TextMessage textMessage = (TextMessage) message;
		try {
			simpleMailMessage.setTo(textMessage.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		sendEmail();
	}

	@JmsListener(destination = "fixedDepositDestination")
	public void processFixedDeposit(Message message) {
		logger.info("processFixedDeposit() invoked");
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

	@Transactional(transactionManager = "dbTxManager")
	public int createFixedDeposit(FixedDepositDetails fdd) {
		// -- create fixed deposit
		bankAccountDao.subtractFromAccount(fdd.getBankAccountId(), fdd.getFdAmount());
		return myFixedDepositDao.createFixedDeposit(fdd);
	}
}
