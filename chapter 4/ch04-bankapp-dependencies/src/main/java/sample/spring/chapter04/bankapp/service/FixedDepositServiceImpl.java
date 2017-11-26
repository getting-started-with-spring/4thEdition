package sample.spring.chapter04.bankapp.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import sample.spring.chapter04.bankapp.Constants;
import sample.spring.chapter04.bankapp.dao.FixedDepositDao;
import sample.spring.chapter04.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter04.bankapp.event.EventSender;
import sample.spring.chapter04.bankapp.event.FixedDepositCreatedEvent;

public class FixedDepositServiceImpl implements FixedDepositService {
	private static Logger logger = LogManager
			.getLogger(FixedDepositServiceImpl.class);

	private FixedDepositDao fixedDepositDao;
	private EventSender eventSender;

	public FixedDepositServiceImpl(String configFile) throws Exception {
		// --read the appConfig.properties file to know the EventSender
		// implementation to use
		ClassPathResource configProperties = new ClassPathResource(configFile);

		if (configProperties.exists()) {
			InputStream inStream = configProperties.getInputStream();
			Properties properties = new Properties();
			properties.load(inStream);
			String eventSenderClassString = properties
					.getProperty(Constants.EVENT_SENDER_CLASS_PROPERTY);

			if (eventSenderClassString != null) {
				Class<?> eventSenderClass = Class
						.forName(eventSenderClassString);
				eventSender = (EventSender) eventSenderClass.newInstance();
				logger.info("Created EventSender class");

			} else {
				logger.info("appConfig.properties file doesn't contain the information about EventSender class");
			}
		}
	}

	public void setFixedDepositDao(FixedDepositDao fixedDepositDao) {
		this.fixedDepositDao = fixedDepositDao;
	}

	@Override
	public void createFixedDeposit(FixedDepositDetails fdd) throws Exception {
		// -- create fixed deposit
		fixedDepositDao.createFixedDeposit(fdd);

		// -- create the event that corresponds to creating fixed deposits
		FixedDepositCreatedEvent event = new FixedDepositCreatedEvent();
		Map<String, Object> eventData = new HashMap<String, Object>();
		eventData.put("amount", fdd.getDepositAmount());
		event.setEventData(eventData);
		// --send the event to the event sender
		if (eventSender != null) {
			eventSender.sendEvent(event);
		}
	}
}
