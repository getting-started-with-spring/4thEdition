package sample.spring.chapter09.bankapp.repository;

import java.util.List;

import sample.spring.chapter09.bankapp.domain.FixedDepositDetails;

public interface FixedDepositRepositoryCustom {
	List<FixedDepositDetails> findByTenure(int tenure);
}
