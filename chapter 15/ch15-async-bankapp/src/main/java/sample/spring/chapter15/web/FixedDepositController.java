package sample.spring.chapter15.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sample.spring.chapter15.domain.FixedDepositDetails;
import sample.spring.chapter15.service.FixedDepositService;

@Controller
@RequestMapping(value = "/fixedDeposit")
public class FixedDepositController {

	@Autowired
	private FixedDepositService fixedDepositService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Callable<ModelAndView> listFixedDeposits() {
		return new Callable<ModelAndView>() {

			@Override
			public ModelAndView call() throws Exception {
				Thread.sleep(5000);
				Map<String, List<FixedDepositDetails>> modelData = new HashMap<String, List<FixedDepositDetails>>();
				modelData.put("fdList", fixedDepositService.getFixedDeposits());
				return new ModelAndView("fixedDepositList", modelData);
			}
		};
	}

	@RequestMapping(params = "fdAction=createFDForm", method = RequestMethod.POST)
	public Callable<ModelAndView> showOpenFixedDepositForm() {
		return new Callable<ModelAndView>() {

			@Override
			public ModelAndView call() throws Exception {
				Thread.sleep(5000);
				FixedDepositDetails fixedDepositDetails = new FixedDepositDetails();
				fixedDepositDetails.setEmail("You must enter a valid email");
				ModelMap modelData = new ModelMap();
				modelData.addAttribute(fixedDepositDetails);
				return new ModelAndView("createFixedDepositForm", modelData);
			}
		};
	}

	@RequestMapping(params = "fdAction=create", method = RequestMethod.POST)
	public Callable<ModelAndView> openFixedDeposit(
			final @RequestParam Map<String, String> params) {
		return new Callable<ModelAndView>() {

			@Override
			public ModelAndView call() throws Exception {
				Thread.sleep(5000);
				String depositAmount = params.get("depositAmount");
				String tenure = params.get("tenure");
				String email = params.get("email");

				Map<String, Object> modelData = new HashMap<String, Object>();

				if (!NumberUtils.isNumber(depositAmount)) {
					modelData
							.put("error.depositAmount", "enter a valid number");
				} else if (NumberUtils.toInt(depositAmount) < 1000) {
					modelData.put("error.depositAmount",
							"must be greater than or equal to 1000");
				}

				if (!NumberUtils.isNumber(tenure)) {
					modelData.put("error.tenure", "enter a valid number");
				} else if (NumberUtils.toInt(tenure) < 12) {
					modelData.put("error.tenure",
							"must be greater than or equal to 12");
				}

				if (email == null || "".equalsIgnoreCase(email)) {
					modelData.put("error.email", "must not be blank");
				} else if (!email.contains("@")) {
					modelData.put("error.email",
							"not a well-formed email address");
				}

				FixedDepositDetails fixedDepositDetails = new FixedDepositDetails();
				fixedDepositDetails.setDepositAmount(depositAmount);
				fixedDepositDetails.setEmail(email);
				fixedDepositDetails.setTenure(tenure);

				if (modelData.size() > 0) { // --this means there are validation
											// errors
					modelData.put("fixedDepositDetails", fixedDepositDetails);
					return new ModelAndView("createFixedDepositForm", modelData);
				} else {
					fixedDepositService.saveFixedDeposit(fixedDepositDetails);
					return new ModelAndView("redirect:/fixedDeposit/list");
				}
			}
		};
	}

	@RequestMapping(params = "fdAction=edit", method = RequestMethod.POST)
	public Callable<ModelAndView> editFixedDeposit(
			final @RequestParam MultiValueMap<String, String> params) {
		return new Callable<ModelAndView>() {

			@Override
			public ModelAndView call() throws Exception {
				Thread.sleep(5000);
				String depositAmount = params.get("depositAmount").get(0);
				String tenure = params.get("tenure").get(0);
				String email = params.get("email").get(0);
				String id = params.get("id").get(0);

				Map<String, Object> modelData = new HashMap<String, Object>();

				if (!NumberUtils.isNumber(depositAmount)) {
					modelData
							.put("error.depositAmount", "enter a valid number");
				} else if (NumberUtils.toInt(depositAmount) < 1000) {
					modelData.put("error.depositAmount",
							"must be greater than or equal to 1000");
				}

				if (!NumberUtils.isNumber(tenure)) {
					modelData.put("error.tenure", "enter a valid number");
				} else if (NumberUtils.toInt(tenure) < 12) {
					modelData.put("error.tenure",
							"must be greater than or equal to 12");
				}

				if (email == null || "".equalsIgnoreCase(email)) {
					modelData.put("error.email", "must not be blank");
				} else if (!email.contains("@")) {
					modelData.put("error.email",
							"not a well-formed email address");
				}

				FixedDepositDetails fixedDepositDetails = new FixedDepositDetails();
				fixedDepositDetails.setId(Integer.parseInt(id));
				fixedDepositDetails.setDepositAmount(depositAmount);
				fixedDepositDetails.setEmail(email);
				fixedDepositDetails.setTenure(tenure);

				if (modelData.size() > 0) { // --this means there are validation
											// errors
					modelData.put("fixedDepositDetails", fixedDepositDetails);
					return new ModelAndView("editFixedDepositForm", modelData);
				} else {
					fixedDepositService.editFixedDeposit(fixedDepositDetails);
					return new ModelAndView("redirect:/fixedDeposit/list");
				}
			}
		};
	}

	@RequestMapping(params = "fdAction=close", method = RequestMethod.GET)
	public Callable<String> closeFixedDeposit(
			final @RequestParam(value = "fixedDepositId") int fdId) {
		return new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(5000);
				fixedDepositService.closeFixedDeposit(fdId);
				return "redirect:/fixedDeposit/list";
			}
		};
	}

	@RequestMapping(params = "fdAction=view", method = RequestMethod.GET)
	public Callable<ModelAndView> viewFixedDepositDetails(
			final HttpServletRequest request) {
		return new Callable<ModelAndView>() {

			@Override
			public ModelAndView call() throws Exception {
				Thread.sleep(5000);
				FixedDepositDetails fixedDepositDetails = fixedDepositService
						.getFixedDeposit(Integer.parseInt(request
								.getParameter("fixedDepositId")));
				ModelMap modelMap = new ModelMap();
				modelMap.addAttribute(fixedDepositDetails);
				return new ModelAndView("editFixedDepositForm", modelMap);
			}
		};
	}

	@ExceptionHandler
	public String handleException(Exception ex) {
		return "error";
	}
}