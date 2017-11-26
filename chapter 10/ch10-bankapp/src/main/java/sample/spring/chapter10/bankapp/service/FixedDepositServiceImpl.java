package sample.spring.chapter10.bankapp.service;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.spring.chapter10.bankapp.dao.FixedDepositDao;
import sample.spring.chapter10.bankapp.domain.FixedDepositDetails;

@Service(value = "fixedDepositService")
public class FixedDepositServiceImpl implements FixedDepositService {
	private static Logger logger = LogManager.getLogger(FixedDepositServiceImpl.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier(value = "fixedDepositDao")
	private FixedDepositDao myFixedDepositDao;

	@Override
	@Transactional(transactionManager = "jmsTxManager")
	@CacheEvict(cacheNames = { "fixedDepositList" }, allEntries = true, beforeInvocation = true)
	public void createFixedDeposit(final FixedDepositDetails fdd) throws Exception {
		logger.info("createFixedDeposit method invoked");
		jmsTemplate.send("emailQueueDestination", new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage();
				textMessage.setText(fdd.getEmail());
				return textMessage;
			}
		});

		// --this JMS message goes to the default destination configured for the
		// JmsTemplate
		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage = session.createObjectMessage();
				objectMessage.setObject(fdd);
				return objectMessage;
			}
		});
	}

	@Override
	@CachePut(cacheNames = { "fixedDeposit" }, key = "#fixedDepositId")
	public FixedDepositDetails getFixedDeposit(int fixedDepositId) {
		logger.info("getFixedDeposit method invoked with fixedDepositId " + fixedDepositId);
		return myFixedDepositDao.getFixedDeposit(fixedDepositId);
	}

	@Override
	@Cacheable(cacheNames = { "fixedDeposit" }, key = "#fixedDepositId")
	public FixedDepositDetails getFixedDepositFromCache(int fixedDepositId) {
		logger.info("getFixedDepositFromCache method invoked with fixedDepositId " + fixedDepositId);
		throw new RuntimeException(
				"This method throws exception because FixedDepositDetails object must come from the cache");
	}

	@Cacheable(cacheNames = { "fixedDepositList" })
	public List<FixedDepositDetails> findFixedDepositsByBankAccount(int bankAccountId) {
		logger.info("findFixedDepositsByBankAccount method invoked");
		return myFixedDepositDao.findFixedDepositsByBankAccount(bankAccountId);
	}
}
