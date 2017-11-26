package sample.spring.chapter07.bankapp.controller;

import sample.spring.chapter07.bankapp.domain.FixedDepositDetails;

public interface FixedDepositController {
	boolean submit();

	FixedDepositDetails get();
}
