package sample.spring.chapter03.bankapp.controller;

import sample.spring.chapter03.bankapp.domain.BankStatement;

public interface PersonalBankingController {
	BankStatement getMiniStatement();
}
