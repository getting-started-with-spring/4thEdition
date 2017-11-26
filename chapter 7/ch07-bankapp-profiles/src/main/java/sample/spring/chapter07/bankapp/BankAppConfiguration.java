package sample.spring.chapter07.bankapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import sample.spring.chapter07.bankapp.controller.FixedDepositController;
import sample.spring.chapter07.bankapp.controller.FixedDepositControllerImpl;
import sample.spring.chapter07.bankapp.dao.FixedDepositDao;
import sample.spring.chapter07.bankapp.dao.FixedDepositHibernateDao;
import sample.spring.chapter07.bankapp.dao.FixedDepositMyBatisDao;
import sample.spring.chapter07.bankapp.domain.DataSource;
import sample.spring.chapter07.bankapp.service.FixedDepositService;
import sample.spring.chapter07.bankapp.service.FixedDepositServiceImpl;

@Configuration
public class BankAppConfiguration {
	private static Logger logger = LogManager.getLogger(BankAppConfiguration.class);
	
	@Bean
	public FixedDepositController fixedDepositController(FixedDepositService fixedDepositService) {
		FixedDepositControllerImpl controller = new FixedDepositControllerImpl();
		controller.setFixedDepositService(fixedDepositService);
		return controller;
	}

	@Bean
	@Profile({ "hibernate", "default" })
	public FixedDepositDao fixedDepositHibernateDao(DataSource dataSource) {
		logger.info("creating FixedDepositHibernateDao. Database URL is - " + dataSource.getUrl());
		return new FixedDepositHibernateDao(dataSource);
	}

	@Bean
	@Profile({ "mybatis" })
	public FixedDepositDao fixedDepositMyBatisDao(DataSource dataSource) {
		logger.info("creating FixedDepositMyBatisDao. Database URL is - " + dataSource.getUrl());
		return new FixedDepositMyBatisDao(dataSource);
	}

	@Bean
	public FixedDepositService fixedDepositService(FixedDepositDao fixedDepositDao) {
		FixedDepositServiceImpl service = new FixedDepositServiceImpl();
		service.setFixedDepositDao(fixedDepositDao);
		return service;
	}
}
