package sample.spring.chapter06.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sample.spring.chapter06.bankapp.annotation.BankType;
import sample.spring.chapter06.bankapp.annotation.FundTransfer;
import sample.spring.chapter06.bankapp.annotation.TransferSpeed;
import sample.spring.chapter06.bankapp.domain.Account;

@Component
public class FundTransferProcessor {
	@Autowired
	@FundTransfer(transferSpeed = TransferSpeed.IMMEDIATE, bankType = BankType.SAME)
	private FundTransferService sameBankImmediateFundTransferService;
	
	@Autowired
	@FundTransfer(transferSpeed = TransferSpeed.IMMEDIATE, bankType = BankType.DIFFERENT)
	private FundTransferService diffBankImmediateFundTransferService;

	public void sameBankImmediateFundTransferService() {
		sameBankImmediateFundTransferService.transferFunds(new Account(), new Account());
	}
	
	public void diffBankImmediateFundTransferService() {
		diffBankImmediateFundTransferService.transferFunds(new Account(), new Account());
	}
	
}
