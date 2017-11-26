package sample.spring.chapter05.bankapp.dao;

public interface FixedDepositDao {
	boolean createFixedDeposit(long id, float depositAmount, int tenure,
			String email);
}
