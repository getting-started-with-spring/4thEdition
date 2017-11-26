package sample.spring.chapter15.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.async.DeferredResult;

import sample.spring.chapter15.domain.FixedDepositDetails;
import sample.spring.chapter15.exception.ValidationException;
import sample.spring.chapter15.service.FixedDepositService;

@Controller
@RequestMapping(path = "/fixedDeposits")
public class FixedDepositController {
	private static Logger logger = LogManager
			.getLogger(FixedDepositController.class);

	private static final String LIST_METHOD = "getFixedDepositList";
	private static final String GET_FD_METHOD = "getFixedDeposit";
	private static final String OPEN_FD_METHOD = "openFixedDeposit";
	private static final String EDIT_FD_METHOD = "editFixedDeposit";
	private static final String CLOSE_FD_METHOD = "closeFixedDeposit";

	@SuppressWarnings("rawtypes")
	private final Queue<ResultContext> deferredResultQueue = new ConcurrentLinkedQueue<ResultContext>();

	@Autowired
	private FixedDepositService fixedDepositService;

	@RequestMapping(method = RequestMethod.GET)
	public DeferredResult<ResponseEntity<List<FixedDepositDetails>>> getFixedDepositList() {
		logger.info("listFixedDeposits() method: Getting list of fixed deposits");

		DeferredResult<ResponseEntity<List<FixedDepositDetails>>> dr = new DeferredResult<ResponseEntity<List<FixedDepositDetails>>>();

		ResultContext<ResponseEntity<List<FixedDepositDetails>>> resultContext = new ResultContext<ResponseEntity<List<FixedDepositDetails>>>();
		resultContext.setDeferredResult(dr);
		resultContext.setMethodToInvoke(LIST_METHOD);
		resultContext.setArgs(new HashMap<String, Object>());

		deferredResultQueue.add(resultContext);
		return dr;
	}

	@RequestMapping(method = RequestMethod.GET, params = "id")
	public DeferredResult<ResponseEntity<FixedDepositDetails>> getFixedDeposit(
			@RequestParam("id") int id) {
		logger.info("getFixedDeposit() method: Getting fixed deposit with id "
				+ id);

		DeferredResult<ResponseEntity<FixedDepositDetails>> dr = new DeferredResult<ResponseEntity<FixedDepositDetails>>();

		ResultContext<ResponseEntity<FixedDepositDetails>> resultContext = new ResultContext<ResponseEntity<FixedDepositDetails>>();
		resultContext.setDeferredResult(dr);
		resultContext.setMethodToInvoke(GET_FD_METHOD);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("fixedDepositId", id);
		resultContext.setArgs(args);

		deferredResultQueue.add(resultContext);
		return dr;
	}

	@RequestMapping(method = RequestMethod.POST)
	public DeferredResult<ResponseEntity<FixedDepositDetails>> openFixedDeposit(
			@RequestBody FixedDepositDetails fixedDepositDetails,
			BindingResult bindingResult) {

		DeferredResult<ResponseEntity<FixedDepositDetails>> dr = new DeferredResult<ResponseEntity<FixedDepositDetails>>();

		ResultContext<ResponseEntity<FixedDepositDetails>> resultContext = new ResultContext<ResponseEntity<FixedDepositDetails>>();
		resultContext.setDeferredResult(dr);
		resultContext.setMethodToInvoke(OPEN_FD_METHOD);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("fixedDepositDetails", fixedDepositDetails);
		args.put("bindingResult", bindingResult);
		resultContext.setArgs(args);

		deferredResultQueue.add(resultContext);
		return dr;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public DeferredResult<ResponseEntity<FixedDepositDetails>> editFixedDeposit(
			@RequestBody FixedDepositDetails fixedDepositDetails,
			@RequestParam("id") int fixedDepositId, BindingResult bindingResult) {

		DeferredResult<ResponseEntity<FixedDepositDetails>> dr = new DeferredResult<ResponseEntity<FixedDepositDetails>>();

		ResultContext<ResponseEntity<FixedDepositDetails>> resultContext = new ResultContext<ResponseEntity<FixedDepositDetails>>();
		resultContext.setDeferredResult(dr);
		resultContext.setMethodToInvoke(EDIT_FD_METHOD);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("fixedDepositDetails", fixedDepositDetails);
		args.put("bindingResult", bindingResult);
		args.put("fixedDepositId", fixedDepositId);
		resultContext.setArgs(args);

		deferredResultQueue.add(resultContext);
		return dr;
	}

	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseStatus(code = HttpStatus.OK)
	public DeferredResult<HttpEntity<String>> closeFixedDeposit(
			@RequestParam(value = "id") int fixedDepositId) {

		DeferredResult<HttpEntity<String>> dr = new DeferredResult<HttpEntity<String>>();

		ResultContext<HttpEntity<String>> resultContext = new ResultContext<HttpEntity<String>>();
		resultContext.setDeferredResult(dr);
		resultContext.setMethodToInvoke(CLOSE_FD_METHOD);

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("fixedDepositId", fixedDepositId);
		resultContext.setArgs(args);

		deferredResultQueue.add(resultContext);
		return dr;
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String handleException(Exception ex) {
		logger.info("handling ValidationException " + ex.getMessage());
		return ex.getMessage();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Scheduled(fixedRate = 10000)
	public void processResults() {
		for (ResultContext resultContext : deferredResultQueue) {
			if (resultContext.getMethodToInvoke() == LIST_METHOD) {
				resultContext.getDeferredResult().setResult(
						new ResponseEntity<List<FixedDepositDetails>>(
								fixedDepositService.getFixedDeposits(),
								HttpStatus.OK));
			}
			if (resultContext.getMethodToInvoke() == GET_FD_METHOD) {
				int fixedDepositId = (Integer) resultContext.getArgs().get(
						"fixedDepositId");
				resultContext.getDeferredResult().setResult(
						new ResponseEntity<FixedDepositDetails>(
								fixedDepositService
										.getFixedDeposit(fixedDepositId),
								HttpStatus.OK));
			}

			if (resultContext.getMethodToInvoke() == OPEN_FD_METHOD) {
				FixedDepositDetails fixedDepositDetails = (FixedDepositDetails) resultContext
						.getArgs().get("fixedDepositDetails");
				BindingResult bindingResult = (BindingResult) resultContext
						.getArgs().get("bindingResult");

				new FixedDepositDetailsValidator().validate(
						fixedDepositDetails, bindingResult);

				if (bindingResult.hasErrors()) {
					logger.info("openFixedDeposit() method: Validation errors occurred");
					resultContext.getDeferredResult().setErrorResult(
							new ValidationException(
									"Validation errors occurred"));
				} else {
					fixedDepositService.saveFixedDeposit(fixedDepositDetails);
					resultContext.getDeferredResult().setResult(
							new ResponseEntity<FixedDepositDetails>(
									fixedDepositDetails, HttpStatus.CREATED));
					logger.info("openFixedDeposit() method: Fixed deposit details successfully saved.");
				}
			}
			if (resultContext.getMethodToInvoke() == EDIT_FD_METHOD) {
				FixedDepositDetails fixedDepositDetails = (FixedDepositDetails) resultContext
						.getArgs().get("fixedDepositDetails");
				int fixedDepositId = (Integer) resultContext.getArgs().get(
						"fixedDepositId");
				BindingResult bindingResult = (BindingResult) resultContext
						.getArgs().get("bindingResult");

				fixedDepositDetails.setId(fixedDepositId);
				new FixedDepositDetailsValidator().validate(
						fixedDepositDetails, bindingResult);

				if (bindingResult.hasErrors()) {
					logger.info("editFixedDeposit() method: Validation errors occurred");
					resultContext.getDeferredResult().setResult(
							new ValidationException(
									"Validation errors occurred"));
				} else {
					fixedDepositService.editFixedDeposit(fixedDepositDetails);
					logger.info("editFixedDeposit() method: Fixed deposit details successfully changed.");
					resultContext.getDeferredResult().setResult(
							new ResponseEntity<FixedDepositDetails>(
									fixedDepositDetails, HttpStatus.OK));
				}
			}
			if (resultContext.getMethodToInvoke() == CLOSE_FD_METHOD) {
				int fixedDepositId = (Integer) resultContext.getArgs().get(
						"fixedDepositId");
				fixedDepositService.closeFixedDeposit(fixedDepositId);
				logger.info("closeFixedDeposit() method: Fixed deposit successfully closed.");
				resultContext.getDeferredResult().setResult(
						new HttpEntity<String>(
								"Successfully deleted the fixed deposit"));
			}
			deferredResultQueue.remove(resultContext);
		}
	}
}