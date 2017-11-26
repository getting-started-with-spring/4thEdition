package sample.spring.chapter09.bankapp.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import sample.spring.chapter09.bankapp.domain.FixedDepositDetails;


public interface FixedDepositService {
	int createFixedDeposit(FixedDepositDetails fdd) throws Exception;
	FixedDepositDetails getFixedDeposit(int fixedDepositId);
	long count();
	long countByTenure(int tenure);
	List<FixedDepositDetails> removeByTenure(int tenure);
	List<FixedDepositDetails> findByTenure(int tenure);
	List<FixedDepositDetails> findByTenureLessThan(int tenure);
	List<FixedDepositDetails> findByFdAmountGreaterThan(int fdAmount);
	List<FixedDepositDetails> findTop2ByTenure(int tenure);
	List<FixedDepositDetails> findTop2ByOrderByFdCreationDateDesc();
	List<FixedDepositDetails> findByTenureAndFdAmount(int tenure, int fdAmount);
	List<FixedDepositDetails> findByCustomQuery(int tenure, int fdAmount, String active);
	List<FixedDepositDetails> findByTenure(int tenure, Pageable pageable);
	List<FixedDepositDetails> findByTenure(int tenure, Sort sort);
	Slice<FixedDepositDetails> findByFdAmount(int amount, Pageable pageable);
	Stream<FixedDepositDetails> findAllByTenure(int tenure);
	CompletableFuture<List<FixedDepositDetails>> findAllByFdAmount(int fdAmount);
	Iterable<FixedDepositDetails> getHighValueFds();
	Iterable<FixedDepositDetails> getAllFds();
}
