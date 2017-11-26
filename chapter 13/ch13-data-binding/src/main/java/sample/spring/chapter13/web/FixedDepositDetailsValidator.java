package sample.spring.chapter13.web;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sample.spring.chapter13.domain.FixedDepositDetails;

public class FixedDepositDetailsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FixedDepositDetails.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FixedDepositDetails fixedDepositDetails = (FixedDepositDetails) target;

		long depositAmount = fixedDepositDetails.getDepositAmount();
		String email = fixedDepositDetails.getEmail();

		if (depositAmount < 1000) {
			errors.rejectValue("depositAmount", "error.depositAmount.less",
					"must be greater than or equal to 1000");
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
