package sample.spring.chapter06.bankapp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sample.spring.chapter06.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter06.bankapp.service.FixedDepositService;

@Controller
public class FixedDepositControllerImpl implements FixedDepositController {
	private static Logger logger = LogManager.getLogger(FixedDepositControllerImpl.class);
	 
	@Autowired
	private FixedDepositService fixedDepositService;
	
	public FixedDepositControllerImpl() {
		logger.info("initializing");
	}
	
	public boolean submit() {
		return fixedDepositService.createFixedDeposit(new FixedDepositDetails(
				1, 10000, 365, "someemail@something.com"));
	}
	
	public FixedDepositDetails get() {
		return fixedDepositService.getFixedDepositDetails(1L);
	}
}
