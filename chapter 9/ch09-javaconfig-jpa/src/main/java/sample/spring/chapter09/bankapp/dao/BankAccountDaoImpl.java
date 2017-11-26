package sample.spring.chapter09.bankapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import sample.spring.chapter09.bankapp.domain.BankAccountDetails;

@Repository
public class BankAccountDaoImpl implements BankAccountDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public int createBankAccount(final BankAccountDetails bankAccountDetails) {
		entityManager.persist(bankAccountDetails);
		return bankAccountDetails.getAccountId();
	}

	public List<BankAccountDetails> findAccountsWithBalanceGreaterThan(int balanceAmount) {
		return entityManager.createQuery(
				"SELECT details FROM BankAccountDetails details WHERE details.balanceAmount > :balanceAmount",
				BankAccountDetails.class).setParameter("balanceAmount", balanceAmount).getResultList();
	}

	@Override
	public void subtractFromAccount(int bankAccountId, int amount) {
		BankAccountDetails bankAccountDetails = entityManager.find(BankAccountDetails.class, bankAccountId);
		if (bankAccountDetails.getBalanceAmount() < amount) {
			throw new RuntimeException("Insufficient balance amount in bank account");
		}
		bankAccountDetails.setBalanceAmount(bankAccountDetails.getBalanceAmount() - amount);
		entityManager.merge(bankAccountDetails);
	}
}
