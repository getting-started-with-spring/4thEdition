package sample.spring.chapter02.bankapp.controller;

import sample.spring.chapter02.bankapp.domain.FixedDepositDetails;
import sample.spring.chapter02.bankapp.service.FixedDepositService;

public interface FixedDepositController {
	FixedDepositService getFixedDepositService();

	boolean submit();

	FixedDepositDetails get();
}
