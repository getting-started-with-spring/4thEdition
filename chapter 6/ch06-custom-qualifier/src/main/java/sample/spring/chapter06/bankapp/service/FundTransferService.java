package sample.spring.chapter06.bankapp.service;

import sample.spring.chapter06.bankapp.domain.Account;

public interface FundTransferService {
	void transferFunds(Account sender, Account receiver);
}
