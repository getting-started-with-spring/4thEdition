package sample.spring.chapter19.bankapp.exception;

public class NotEnoughBalanceException extends RuntimeException {
	private static final long serialVersionUID = -5893190680071808405L;

	public NotEnoughBalanceException(String message) {
		super(message);
	}

}
