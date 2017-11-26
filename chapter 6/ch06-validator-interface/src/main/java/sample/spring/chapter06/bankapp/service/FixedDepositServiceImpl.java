package sample.spring.chapter06.bankapp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;

import sample.spring.chapter06.bankapp.dao.FixedDepositDao;
import sample.spring.chapter06.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter06.bankapp.validator.FixedDepositValidator;

@Service(value = "fixedDepositService")
public class FixedDepositServiceImpl implements FixedDepositService {
	private static Logger logger = LogManager
			.getLogger(FixedDepositServiceImpl.class);

	@Autowired
	@Qualifier(value = "myFixedDepositDao")
	private FixedDepositDao myFixedDepositDao;

	@Override
	public void createFixedDeposit(FixedDepositDetails fixedDepositDetails) throws Exception {
		// -- create fixed deposit
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(
				fixedDepositDetails, "Errors");
		FixedDepositValidator validator = new FixedDepositValidator();
		validator.validate(fixedDepositDetails, bindingResult);
		if (bindingResult.getErrorCount() > 0) {
			logger.error("Errors were found while validating FixedDepositDetails instance");
		} else {
			myFixedDepositDao.createFixedDeposit(fixedDepositDetails);
			logger.info("Created fixed deposit");
		}
	}
}