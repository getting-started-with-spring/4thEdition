package sample.spring.chapter03.bankapp.dao;

import sample.spring.chapter03.bankapp.domain.BankStatement;
import sample.spring.chapter03.bankapp.utils.DatabaseOperations;

public class PersonalBankingDaoImpl implements PersonalBakingDao {
	private DatabaseOperations databaseOperations;

	public void setDatabaseOperations(DatabaseOperations databaseOperations) {
		this.databaseOperations = databaseOperations;
	}

	@Override
	public BankStatement getMiniStatement() {
		return databaseOperations.getMiniStatement();
	}
}
