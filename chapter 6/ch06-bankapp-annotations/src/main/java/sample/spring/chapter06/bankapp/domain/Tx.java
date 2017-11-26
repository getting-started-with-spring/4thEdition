package sample.spring.chapter06.bankapp.domain;

public class Tx {
	private int id; // -- transaction id
	private String type; // -- transaction type
	private String status; // -- transaction status

	public Tx(int id, String type, String status) {
		this.id = id;
		this.type = type;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Tx [id=" + id + ", type=" + type + ", status=" + status + "]";
	}
}