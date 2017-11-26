package sample.spring.chapter08.bankapp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import sample.spring.chapter08.bankapp.domain.FixedDepositDetails;

@Repository(value = "fixedDepositDao")
public class FixedDepositDaoImpl implements FixedDepositDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public int createFixedDeposit(final FixedDepositDetails fixedDepositDetails) {
		sessionFactory.getCurrentSession().save(fixedDepositDetails);
		return fixedDepositDetails.getFixedDepositId();
	}

	public FixedDepositDetails getFixedDeposit(final int fixedDepositId) {
		String hql = "from FixedDepositDetails as fixedDepositDetails where fixedDepositDetails.fixedDepositId ="
				+ fixedDepositId;
		return (FixedDepositDetails) sessionFactory.getCurrentSession()
				.createQuery(hql).uniqueResult();
	}
}
