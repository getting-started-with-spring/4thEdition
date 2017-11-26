package sample.reactiveapp.hotcold;

public class StockQuote {
	private String symbol;
	private float price;

	public StockQuote(String symbol, float price) {
		super();
		this.symbol = symbol;
		this.price = price;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
