package sample.spring.chapter03.bankapp.service;

import java.beans.ConstructorProperties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")
public class TransferFundsServiceImpl implements TransferFundsService {
	private static Logger logger = LogManager.getLogger(TransferFundsServiceImpl.class);

	private String webServiceUrl;
	private boolean active;
	private long timeout;
	private int numberOfRetrialAttempts;

	@ConstructorProperties({ "webServiceUrl", "active", "timeout", "numberOfRetrialAttempts" })
	public TransferFundsServiceImpl(String webServiceUrl, boolean active, long timeout, int numberOfRetrialAttempts) {
		this.webServiceUrl = webServiceUrl;
		this.active = active;
		this.timeout = timeout;
		this.numberOfRetrialAttempts = numberOfRetrialAttempts;

		logger.info("Web Service URL: " + webServiceUrl + ", active : " + active + ", timeout : " + timeout
				+ ", numberOfRetrialAttempts " + numberOfRetrialAttempts);
	}

	public void transferFunds() {

	}
}
