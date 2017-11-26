package sample.spring.chapter09.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.spring.chapter09.bankapp.domain.BankAccountDetails;
import sample.spring.chapter09.bankapp.repository.BankAccountRepository;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountRepository bankAccountRepository;
	
	@Override
	@Transactional
	public int createBankAccount(BankAccountDetails bankAccountDetails) {
		return bankAccountRepository.save(bankAccountDetails).getAccountId();
	}
}
