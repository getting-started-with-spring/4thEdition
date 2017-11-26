package sample.spring.chapter09.bankapp.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.scheduling.annotation.Async;

import sample.spring.chapter09.bankapp.domain.FixedDepositDetails;

public interface FixedDepositRepository extends Repository<FixedDepositDetails, Integer>,
		QuerydslPredicateExecutor<FixedDepositDetails>, QueryByExampleExecutor<FixedDepositDetails> {

	FixedDepositDetails save(FixedDepositDetails entity);

	FixedDepositDetails findById(Integer id);

	long count();

	long countByTenure(int tenure);

	List<FixedDepositDetails> removeByTenure(int tenure);

	List<FixedDepositDetails> findByTenure(int tenure);

	List<FixedDepositDetails> findByTenureLessThan(int tenure);

	List<FixedDepositDetails> findByFdAmountGreaterThan(int fdAmount);

	List<FixedDepositDetails> findTop2ByOrderByFdCreationDateDesc();

	List<FixedDepositDetails> findTop2ByTenure(int tenure);

	List<FixedDepositDetails> findByTenureAndFdAmount(int tenure, int fdAmount);

	List<FixedDepositDetails> findByTenure(int tenure, Pageable pageable);

	List<FixedDepositDetails> findByTenure(int tenure, Sort sort);

	Slice<FixedDepositDetails> findByFdAmount(int amount, Pageable pageable);

	Page<FixedDepositDetails> findByFdAmountGreaterThan(int amount, Pageable pageable);

	Stream<FixedDepositDetails> findAllByTenure(int tenure);

	@Async
	CompletableFuture<List<FixedDepositDetails>> findAllByFdAmount(int fdAmount);

	@Query("select fd from FixedDepositDetails fd where fd.tenure = ?1 and fd.fdAmount <= ?2 and fd.active = ?3")
	List<FixedDepositDetails> findByCustomQuery(int tenure, int fdAmount, String active);
}
