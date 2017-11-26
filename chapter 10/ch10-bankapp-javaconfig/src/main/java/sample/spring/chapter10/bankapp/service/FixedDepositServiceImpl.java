package sample.spring.chapter10.bankapp.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.spring.chapter10.bankapp.dao.FixedDepositDao;
import sample.spring.chapter10.bankapp.domain.FixedDepositDetails;

@Service(value = "fixedDepositService")
public class FixedDepositServiceImpl implements FixedDepositService {
	private static Logger logger = LogManager.getLogger(FixedDepositServiceImpl.class);

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired
	@Qualifier(value = "fixedDepositDao")
	private FixedDepositDao myFixedDepositDao;

	@Override
	@Transactional(transactionManager = "jmsTxManager")
	@CacheEvict(value = { "fixedDepositList" }, allEntries = true, beforeInvocation = true)
	public void createFixedDeposit(final FixedDepositDetails fdd) throws Exception {
		logger.info("createFixedDeposit method invoked");
		jmsMessagingTemplate.send("emailQueueDestination", MessageBuilder.withPayload(fdd.getEmail()).build());
		// --this JMS message goes to the default destination configured for the
		// JmsTemplate 
		jmsMessagingTemplate.send(MessageBuilder.withPayload(fdd).build());
	}

	@Override
	@CachePut(value = { "fixedDeposit" }, key = "#fixedDepositId")
	public FixedDepositDetails getFixedDeposit(int fixedDepositId) {
		logger.info("getFixedDeposit method invoked with fixedDepositId " + fixedDepositId);
		return myFixedDepositDao.getFixedDeposit(fixedDepositId);
	}

	@Override
	@Cacheable(value = { "fixedDeposit" }, key = "#fixedDepositId")
	public FixedDepositDetails getFixedDepositFromCache(int fixedDepositId) {
		logger.info("getFixedDepositFromCache method invoked with fixedDepositId " + fixedDepositId);
		throw new RuntimeException(
				"This method throws exception because FixedDepositDetails object must come from the cache");
	}

	@Cacheable(value = { "fixedDepositList" })
	public List<FixedDepositDetails> findFixedDepositsByBankAccount(int bankAccountId) {
		logger.info("findFixedDepositsByBankAccount method invoked");
		return myFixedDepositDao.findFixedDepositsByBankAccount(bankAccountId);
	}
}
