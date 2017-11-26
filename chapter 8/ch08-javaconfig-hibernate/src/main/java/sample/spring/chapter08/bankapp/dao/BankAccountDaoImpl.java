package sample.spring.chapter08.bankapp.dao;

import org.hibernate.SessionFactory;

import sample.spring.chapter08.bankapp.domain.BankAccountDetails;

public class BankAccountDaoImpl implements BankAccountDao {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public int createBankAccount(final BankAccountDetails bankAccountDetails) {
		sessionFactory.getCurrentSession().save(bankAccountDetails);
		return bankAccountDetails.getAccountId();
	}

	@Override
	public void subtractFromAccount(int bankAccountId, int amount) {
		String hql = "from BankAccountDetails as bankAccountDetails where bankAccountDetails.accountId ="
				+ bankAccountId;
		BankAccountDetails bankAccountDetails = (BankAccountDetails) sessionFactory
				.getCurrentSession().createQuery(hql).uniqueResult();
		bankAccountDetails.setBalanceAmount(bankAccountDetails
				.getBalanceAmount() - amount);
		sessionFactory.getCurrentSession().merge(bankAccountDetails);
	}
}
