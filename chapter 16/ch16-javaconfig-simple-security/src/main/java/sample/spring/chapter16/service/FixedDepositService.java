package sample.spring.chapter16.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import sample.spring.chapter16.domain.FixedDepositDetails;

public interface FixedDepositService {

	@Secured("ROLE_CUSTOMER")
	List<FixedDepositDetails> getFixedDeposits(String user);

	@Secured("ROLE_ADMIN")
	List<FixedDepositDetails> getAllFixedDeposits();

	@Secured("ROLE_CUSTOMER")
	void saveFixedDeposit(FixedDepositDetails fixedDepositDetails);

	@Secured("ROLE_CUSTOMER")
	FixedDepositDetails getFixedDeposit(int fixedDepositId);
	
	@Secured("ROLE_ADMIN")
	void closeFixedDeposit(int fixedDepositId);

	@Secured("ROLE_CUSTOMER")
	void editFixedDeposit(FixedDepositDetails fixedDepositDetails);
}
