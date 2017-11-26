package sample.spring.chapter03.bankapp.service;

import sample.spring.chapter03.bankapp.base.ServiceTemplate;
import sample.spring.chapter03.bankapp.dao.PersonalBakingDao;
import sample.spring.chapter03.bankapp.domain.BankStatement;

public class PersonalBankingServiceImpl extends ServiceTemplate implements PersonalBankingService {

	private PersonalBakingDao personalBakingDao;

	public void setPersonalBankingDao(PersonalBakingDao personalBakingDao) {
		this.personalBakingDao = personalBakingDao;
	}

	@Override
	public BankStatement getMiniStatement() {
		return personalBakingDao.getMiniStatement();
	}
}
