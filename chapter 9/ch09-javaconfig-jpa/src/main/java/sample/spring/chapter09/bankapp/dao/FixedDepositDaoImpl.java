package sample.spring.chapter09.bankapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import sample.spring.chapter09.bankapp.domain.FixedDepositDetails;

@Repository
public class FixedDepositDaoImpl implements FixedDepositDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public int createFixedDeposit(final FixedDepositDetails fixedDepositDetails) {
		entityManager.persist(fixedDepositDetails);
		return fixedDepositDetails.getFixedDepositId();
	}

	public FixedDepositDetails getFixedDeposit(final int fixedDepositId) {
		return entityManager.find(FixedDepositDetails.class, fixedDepositId);
	}
}
