package sample.spring.chapter15.web;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sample.spring.chapter15.domain.FixedDepositDetails;

public class FixedDepositDetailsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FixedDepositDetails.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FixedDepositDetails fixedDepositDetails = (FixedDepositDetails) target;

		String depositAmount = fixedDepositDetails.getDepositAmount();
		String tenure = fixedDepositDetails.getTenure();
		String email = fixedDepositDetails.getEmail();

		if (!NumberUtils.isNumber(depositAmount)) {
			errors.rejectValue("depositAmount", "error.depositAmount.invalid",
					"enter a valid number");
		} else if (NumberUtils.toInt(depositAmount) < 1000) {
			errors.rejectValue("depositAmount", "error.depositAmount.less",
					"must be greater than or equal to 1000");
		}

		if (!NumberUtils.isNumber(tenure)) {
			errors.rejectValue("tenure", "error.tenure.invalid",
					"enter a valid number");
		} else if (NumberUtils.toInt(tenure) < 12) {
			errors.rejectValue("tenure", "error.tenure.less",
					"must be greater than or equal to 12");
		}

		if (email == null || "".equalsIgnoreCase(email)) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
					"error.email.blank", "must not be blank");
		} else if (!email.contains("@")) {
			errors.rejectValue("email", "error.email.invalid",
					"not a well-formed email address");
		}
	}
}
