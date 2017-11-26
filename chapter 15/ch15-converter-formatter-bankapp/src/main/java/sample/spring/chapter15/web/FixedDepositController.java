package sample.spring.chapter15.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sample.spring.chapter15.domain.FixedDepositDetails;
import sample.spring.chapter15.service.FixedDepositService;

@Controller
@RequestMapping(value = "/fixedDeposit")
public class FixedDepositController {
	private static Logger logger = LogManager
			.getLogger(FixedDepositController.class);

	@Autowired
	private FixedDepositService fixedDepositService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listFixedDeposits() {
		ModelMap modelData = new ModelMap();
		modelData.put("fdList", fixedDepositService.getFixedDeposits());
		modelData.put("currentLocale", LocaleContextHolder.getLocale());
		return new ModelAndView("fixedDepositList", modelData);
	}


	@ModelAttribute(value = "newFixedDepositDetails")
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
			@ModelAttribute(value = "newFixedDepositDetails") FixedDepositDetails fixedDepositDetails,
			BindingResult bindingResult) {

		new FixedDepositDetailsValidator().validate(fixedDepositDetails,
				bindingResult);

		if (bindingResult.hasErrors()) {
			logger.info("openFixedDeposit() method: Validation errors - re-displaying form for opening a new fixed deposit");
			return "createFixedDepositForm";
		} else {
			fixedDepositService.saveFixedDeposit(fixedDepositDetails);
			logger.info("openFixedDeposit() method: Fixed deposit details successfully saved. Redirecting to show the list of fixed deposits.");
			return "redirect:/fixedDeposit/list";
		}
	}

	@RequestMapping(params = "fdAction=edit", method = RequestMethod.POST)
	public String editFixedDeposit(
			@ModelAttribute("editableFixedDepositDetails") FixedDepositDetails fixedDepositDetails,
			BindingResult bindingResult) {

		new FixedDepositDetailsValidator().validate(fixedDepositDetails,
				bindingResult);

		if (bindingResult.hasErrors()) {
			logger.info("editFixedDeposit() method: Validation errors - re-displaying form for editing a fixed deposit");
			return "editFixedDepositForm";
		} else {
			fixedDepositService.editFixedDeposit(fixedDepositDetails);
			logger.info("editFixedDeposit() method: Fixed deposit details successfully changed. Redirecting to show the list of fixed deposits.");
			return "redirect:/fixedDeposit/list";
		}
	}

	@RequestMapping(params = "fdAction=close", method = RequestMethod.GET)
	public String closeFixedDeposit(
			@RequestParam(value = "fixedDepositId") int fdId) {
		fixedDepositService.closeFixedDeposit(fdId);
		logger.info("closeFixedDeposit() method: Fixed deposit successfully closed. Redirecting to show the list of fixed deposits.");
		return "redirect:/fixedDeposit/list";
	}

	@RequestMapping(params = "fdAction=view", method = RequestMethod.GET)
	public ModelAndView viewFixedDepositDetails(
			@RequestParam(value = "fixedDepositId") FixedDepositDetails fixedDepositDetails) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("editableFixedDepositDetails", fixedDepositDetails);
		logger.info("viewFixedDepositDetails() method: Fixed deposit details loaded from data store. Showing form for editing the loaded fixed deposit.");
		return new ModelAndView("editFixedDepositForm", modelMap);
	}
}