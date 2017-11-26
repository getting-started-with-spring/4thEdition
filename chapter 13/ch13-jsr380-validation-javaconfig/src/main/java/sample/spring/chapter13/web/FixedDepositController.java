package sample.spring.chapter13.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import sample.spring.chapter13.domain.FixedDepositDetails;
import sample.spring.chapter13.service.FixedDepositService;

@Controller
@RequestMapping(path = "/fixedDeposit")
@SessionAttributes(names = { "newFixedDepositDetails",
		"editableFixedDepositDetails" })
public class FixedDepositController {
	private static Logger logger = LogManager
			.getLogger(FixedDepositController.class);

	@Autowired
	private FixedDepositService fixedDepositService;
	
	@Autowired
	private Validator validator;

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	@ModelAttribute(name = "fdList")
	public List<FixedDepositDetails> listFixedDeposits() {
		logger.info("listFixedDeposits() method: Getting list of fixed deposits");
		return fixedDepositService.getFixedDeposits();
	}

	@ModelAttribute(name = "newFixedDepositDetails")
	public FixedDepositDetails getNewFixedDepositDetails() {
		FixedDepositDetails fixedDepositDetails = new FixedDepositDetails();
		fixedDepositDetails.setEmail("You must enter a valid email");
		logger.info("getNewFixedDepositDetails() method: Returning a new instance of FixedDepositDetails");
		return fixedDepositDetails;
	}

	@RequestMapping(params = "fdAction=createFDForm", method = RequestMethod.POST)
	public String showOpenFixedDepositForm() {
		logger.info("showOpenFixedDepositForm() method: Showing form for opening a new fixed deposit");
		return "createFixedDepositForm";
	}

	@RequestMapping(params = "fdAction=create", method = RequestMethod.POST)
	public String openFixedDeposit(
			@ModelAttribute(name = "newFixedDepositDetails") FixedDepositDetails fixedDepositDetails,
			BindingResult bindingResult, SessionStatus sessionStatus) {
		validator.validate(fixedDepositDetails, bindingResult);
		 
		if (bindingResult.hasErrors()) {
			logger.info("openFixedDeposit() method: Validation errors - re-displaying form for opening a new fixed deposit");
			return "createFixedDepositForm";
		} else {
			fixedDepositService.saveFixedDeposit(fixedDepositDetails);
			sessionStatus.setComplete();
			logger.info("openFixedDeposit() method: Fixed deposit details successfully saved. Redirecting to show the list of fixed deposits.");
			return "redirect:/fixedDeposit/list";
		}
	}

	@RequestMapping(params = "fdAction=edit", method = RequestMethod.POST)
	public String editFixedDeposit(
			@Valid @ModelAttribute("editableFixedDepositDetails") FixedDepositDetails fixedDepositDetails,
			BindingResult bindingResult, SessionStatus sessionStatus) {

		if (bindingResult.hasErrors()) {
			logger.info("editFixedDeposit() method: Validation errors - re-displaying form for editing a fixed deposit");
			return "editFixedDepositForm";
		} else {
			fixedDepositService.editFixedDeposit(fixedDepositDetails);
			sessionStatus.setComplete();
			logger.info("editFixedDeposit() method: Fixed deposit details successfully changed. Redirecting to show the list of fixed deposits.");
			return "redirect:/fixedDeposit/list";
		}
	}

	@RequestMapping(params = "fdAction=close", method = RequestMethod.GET)
	public String closeFixedDeposit(
			@RequestParam(name = "fixedDepositId") int fdId) {
		fixedDepositService.closeFixedDeposit(fdId);
		logger.info("closeFixedDeposit() method: Fixed deposit successfully closed. Redirecting to show the list of fixed deposits.");
		return "redirect:/fixedDeposit/list";
	}

	@RequestMapping(params = "fdAction=view", method = RequestMethod.GET)
	public ModelAndView viewFixedDepositDetails(
			@RequestParam(name = "fixedDepositId") int fixedDepositId) {
		FixedDepositDetails fixedDepositDetails = fixedDepositService
				.getFixedDeposit(fixedDepositId);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("editableFixedDepositDetails", fixedDepositDetails);
		logger.info("viewFixedDepositDetails() method: Fixed deposit details loaded from data store. Showing form for editing the loaded fixed deposit.");
		return new ModelAndView("editFixedDepositForm", modelMap);
	}

	@InitBinder(value = "newFixedDepositDetails")
	public void initBinder_New(WebDataBinder webDataBinder) {
		logger.info("initBinder_New() method: Registering CustomDateEditor");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("MM-dd-yyyy"), false));
	}

	@InitBinder(value = "editableFixedDepositDetails")
	public void initBinder_Edit(WebDataBinder webDataBinder) {
		logger.info("initBinder_Edit() method: Registering CustomDateEditor");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("MM-dd-yyyy"), false));
		webDataBinder.setDisallowedFields("id");
	}
}