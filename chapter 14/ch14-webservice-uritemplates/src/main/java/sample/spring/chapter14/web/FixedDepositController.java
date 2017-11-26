package sample.spring.chapter14.web;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import sample.spring.chapter14.domain.FixedDepositDetails;
import sample.spring.chapter14.exception.ValidationException;
import sample.spring.chapter14.service.FixedDepositService;

@Controller
public class FixedDepositController {
	private static Logger logger = LogManager
			.getLogger(FixedDepositController.class);
	
	@Autowired
	private FixedDepositService fixedDepositService;

	@RequestMapping(path="/fixedDeposits", method = RequestMethod.GET)
	public ResponseEntity<List<FixedDepositDetails>> getFixedDepositList() {
		logger.info("listFixedDeposits() method: Getting list of fixed deposits");
		return new ResponseEntity<List<FixedDepositDetails>>(
				fixedDepositService.getFixedDeposits(), HttpStatus.OK);
	}
	
	@RequestMapping(path="/fixedDeposits/{fixedDepositId}", method = RequestMethod.GET)
	public ResponseEntity<FixedDepositDetails> getFixedDeposit(
			@PathVariable("fixedDepositId") int id) {
		logger.info("getFixedDeposit() method: Getting fixed deposit with id "
				+ id);
		return new ResponseEntity<FixedDepositDetails>(
				fixedDepositService.getFixedDeposit(id), HttpStatus.OK);
	}

	@RequestMapping(path="/fixedDeposits", method = RequestMethod.POST)
	public ResponseEntity<FixedDepositDetails> openFixedDeposit(
			@RequestBody FixedDepositDetails fixedDepositDetails,
			BindingResult bindingResult) {

		new FixedDepositDetailsValidator().validate(fixedDepositDetails,
				bindingResult);

		if (bindingResult.hasErrors()) {
			logger.info("openFixedDeposit() method: Validation errors occurred");
			throw new ValidationException("Validation errors occurred");
		} else {
			fixedDepositService.saveFixedDeposit(fixedDepositDetails);
			logger.info("openFixedDeposit() method: Fixed deposit details successfully saved.");
			return new ResponseEntity<FixedDepositDetails>(fixedDepositDetails,
					HttpStatus.CREATED);
		}
	}

	@RequestMapping(path="/fixedDeposits/{fixedDepositId}", method = RequestMethod.PUT)
	public ResponseEntity<FixedDepositDetails> editFixedDeposit(
			@RequestBody FixedDepositDetails fixedDepositDetails,
			@PathVariable("fixedDepositId") int fixedDepositId, BindingResult bindingResult) {

		fixedDepositDetails.setId(fixedDepositId);
		new FixedDepositDetailsValidator().validate(fixedDepositDetails,
				bindingResult);

		if (bindingResult.hasErrors()) {
			logger.info("editFixedDeposit() method: Validation errors occurred");
			throw new ValidationException("Validation errors occurred");
		} else {
			fixedDepositService.editFixedDeposit(fixedDepositDetails);
			logger.info("editFixedDeposit() method: Fixed deposit details successfully changed.");
			return new ResponseEntity<FixedDepositDetails>(fixedDepositDetails,
					HttpStatus.OK);
		}
	}

	@RequestMapping(path="/fixedDeposits/{fixedDepositId}", method = RequestMethod.DELETE)
	@ResponseStatus(code = HttpStatus.OK)
	public HttpEntity<String> closeFixedDeposit(
			@PathVariable(value = "fixedDepositId") int fdId) {
		fixedDepositService.closeFixedDeposit(fdId);
		logger.info("closeFixedDeposit() method: Fixed deposit successfully closed.");
		return new HttpEntity<String>("Successfully deleted the fixed deposit");
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String handleException(Exception ex) {
		logger.info("handling ValidationException " + ex.getMessage());
		return ex.getMessage();
	}
}