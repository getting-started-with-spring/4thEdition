package sample.spring.chapter06.bankapp.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sample.spring.chapter06.bankapp.domain.FixedDepositDetails;

public class FixedDepositValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FixedDepositDetails.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FixedDepositDetails fdd = (FixedDepositDetails) target;
		if (fdd.getDepositAmount() == 0) {
			errors.reject("zeroDepositAmount");
		}
	}
}
