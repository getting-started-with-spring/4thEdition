package sample.spring.chapter15.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import sample.spring.chapter15.domain.FixedDepositDetails;
import sample.spring.chapter15.service.FixedDepositService;

public class IdToFixedDepositDetailsConverter implements
		Converter<String, FixedDepositDetails> {

	@Autowired
	private FixedDepositService fixedDepositService;

	@Override
	public FixedDepositDetails convert(String source) {
		return fixedDepositService.getFixedDeposit(Integer.parseInt(source));
	}
}