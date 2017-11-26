package sample.spring.chapter06.bankapp.controller;

import sample.spring.chapter06.bankapp.domain.FixedDepositDetails;

public interface FixedDepositController {
	boolean submit();

	FixedDepositDetails get();
}
