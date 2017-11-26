package sample.spring.chapter06.bankapp.dao;

import java.util.List;

import sample.spring.chapter06.bankapp.domain.Tx;

public interface TxDao {
	List<Tx> getTransactions(int accountNumber);
}
