package sample.spring.chapter03.bankapp.controller;

import sample.spring.chapter03.bankapp.domain.BankStatement;
import sample.spring.chapter03.bankapp.service.PersonalBankingService;

public class PersonalBankingControllerImpl implements PersonalBankingController {

	private PersonalBankingService personalBankingService;

	public void setPersonalBankingService(
			PersonalBankingService personalBankingService) {
		this.personalBankingService = personalBankingService;
	}

	@Override
	public BankStatement getMiniStatement() {
		return personalBankingService.getMiniStatement();
	}

}
