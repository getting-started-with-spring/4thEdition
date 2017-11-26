package sample.spring.chapter11.bankapp.dao;

import org.springframework.stereotype.Repository;

import sample.spring.chapter11.bankapp.domain.FixedDepositDetails;

@Repository(value = "fixedDepositDao")
public class FixedDepositDaoImpl implements FixedDepositDao {

	public int createFixedDeposit(final FixedDepositDetails fdd) {
		return 2;
	}

	public FixedDepositDetails getFixedDeposit(final int fixedDepositId) {
		return new FixedDepositDetails(1, 100, 12, "somedomain@someemail.com");
	}

}
