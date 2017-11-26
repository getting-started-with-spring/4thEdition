package sample.spring.chapter03.bankapp.controller;

import sample.spring.chapter03.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter03.bankapp.service.FixedDepositService;

public interface FixedDepositController {
	FixedDepositService getFixedDepositService();

	boolean submit();

	FixedDepositDetails get();
}
