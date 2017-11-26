package sample.spring.chapter06.bankapp.service;

import java.util.List;

import sample.spring.chapter06.bankapp.domain.Tx;

public interface TxService extends MyService {
	List<Tx> getTransactions(int accountNumber);
}
