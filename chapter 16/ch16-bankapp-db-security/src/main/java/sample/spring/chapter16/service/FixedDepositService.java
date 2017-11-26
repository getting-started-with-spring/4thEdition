package sample.spring.chapter16.service;

import java.util.List;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import sample.spring.chapter16.domain.FixedDepositDetails;

public interface FixedDepositService {

	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	List<FixedDepositDetails> getFixedDeposits(String user);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostFilter("hasPermission(filterObject, read) or hasPermission(filterObject, admin)")
	List<FixedDepositDetails> getAllFixedDeposits();

	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	void saveFixedDeposit(FixedDepositDetails fixedDepositDetails);

	@PreAuthorize("hasPermission(#fixedDepositId, 'sample.spring.chapter16.domain.FixedDepositDetails', read) or "
			+ "hasPermission(#fixedDepositId, 'sample.spring.chapter16.domain.FixedDepositDetails', admin)")
	FixedDepositDetails getFixedDeposit(int fixedDepositId);

	@PreAuthorize("hasPermission(#fixedDepositId, 'sample.spring.chapter16.domain.FixedDepositDetails', delete)")
	void closeFixedDeposit(int fixedDepositId);

	@PreAuthorize("hasPermission(#fixedDepositDetails, write)")
	void editFixedDeposit(FixedDepositDetails fixedDepositDetails);
	
	@PreAuthorize("hasPermission(#fixedDepositId, 'sample.spring.chapter16.domain.FixedDepositDetails', write)")
	void provideAccessToAdmin(int fixedDepositId);
}
