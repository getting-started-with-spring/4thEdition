package sample.spring.chapter03.bankapp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sample.spring.chapter03.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter03.bankapp.service.FixedDepositService;

public class FixedDepositControllerImpl implements FixedDepositController {
	private static Logger logger = LogManager.getLogger(FixedDepositControllerImpl.class);
	
	private FixedDepositService fixedDepositService;
	
	public FixedDepositControllerImpl() {
		logger.info("initializing");
	}
	
	public FixedDepositService getFixedDepositService() {
		return fixedDepositService;
	}
	
	public void setFixedDepositService(FixedDepositService fixedDepositService) {
		logger.info("Setting fixedDepositService property");
		this.fixedDepositService = fixedDepositService;
	}
	
	public boolean submit(FixedDepositDetails fixedDepositDetails) {
		return fixedDepositService.createFixedDeposit(fixedDepositDetails);
	}
	
	public FixedDepositDetails get() {
		return fixedDepositService.getFixedDepositDetails(1L);
	}
}
