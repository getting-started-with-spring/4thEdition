package sample.spring.chapter07.bankapp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sample.spring.chapter07.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter07.bankapp.service.FixedDepositService;

public class FixedDepositControllerImpl implements FixedDepositController {
	private static Logger logger = LogManager.getLogger(FixedDepositControllerImpl.class);
	 
	private FixedDepositService fixedDepositService;
	
	public FixedDepositControllerImpl() {
		logger.info("initializing");
	}

	public void setFixedDepositService(FixedDepositService fixedDepositService) {
		this.fixedDepositService = fixedDepositService;
	}
	
	public boolean submit() {
		return fixedDepositService.createFixedDeposit(new FixedDepositDetails(
				1, 10000, 365, "someemail@something.com"));
	}
	
	public FixedDepositDetails get() {
		return fixedDepositService.getFixedDepositDetails(1L);
	}
}
