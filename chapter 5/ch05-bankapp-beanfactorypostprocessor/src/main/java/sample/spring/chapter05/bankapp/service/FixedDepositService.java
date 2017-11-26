package sample.spring.chapter05.bankapp.service;

public interface FixedDepositService {
	void createFixedDeposit(long id, float depositAmount, int tenure,
			String email) throws Exception;
}
