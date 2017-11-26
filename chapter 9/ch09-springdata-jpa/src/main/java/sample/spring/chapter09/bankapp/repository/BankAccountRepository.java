package sample.spring.chapter09.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import sample.spring.chapter09.bankapp.domain.BankAccountDetails;
import sample.spring.chapter09.bankapp.domain.FixedDepositDetails;

public interface BankAccountRepository extends JpaRepository<BankAccountDetails, Integer>, BankAccountRepositoryCustom,
		QuerydslPredicateExecutor<FixedDepositDetails> {
	
}
