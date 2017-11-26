package sample.spring.chapter03.bankapp.service;

import java.beans.ConstructorProperties;

import sample.spring.chapter03.bankapp.base.EmailMessageSender;
import sample.spring.chapter03.bankapp.base.JmsMessageSender;
import sample.spring.chapter03.bankapp.base.ServiceTemplate;
import sample.spring.chapter03.bankapp.base.WebServiceInvoker;
import sample.spring.chapter03.bankapp.dao.PersonalBakingDao;
import sample.spring.chapter03.bankapp.domain.BankStatement;

public class PersonalBankingServiceImpl extends ServiceTemplate implements
		PersonalBankingService {

	private PersonalBakingDao personalBakingDao;

	@ConstructorProperties({"jmsMessageSender","emailMessageSender","webServiceInvoker"})
	public PersonalBankingServiceImpl(JmsMessageSender jmsMessageSender,
			EmailMessageSender emailMessageSender,
			WebServiceInvoker webServiceInvoker) {
		super(jmsMessageSender, emailMessageSender, webServiceInvoker);
	}

	public void setPersonalBankingDao(PersonalBakingDao personalBakingDao) {
		this.personalBakingDao = personalBakingDao;
	}

	@Override
	public BankStatement getMiniStatement() {
		return personalBakingDao.getMiniStatement();
	}
}
