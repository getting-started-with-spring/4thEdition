package sample.reactiveapp.hotcold;

import java.util.HashMap;
import java.util.Map;

public class StockQuoteSupplier {
	private static Map<String, StockQuote> stockQuotes = new HashMap<String, StockQuote>();

	static {
		stockQuotes.put("XX", new StockQuote("XX", 10));
		stockQuotes.put("YY", new StockQuote("YY", 50));
		stockQuotes.put("ZZ", new StockQuote("ZZ", 100));
		stockQuotes.put("AA", new StockQuote("AA", 100));
	}

	public static StockQuote getStockQuote(String symbol) {
		StockQuote stockQuote = stockQuotes.get(symbol);
		stockQuote.setPrice(stockQuote.getPrice() + 5);
		return new StockQuote(symbol, stockQuote.getPrice());
	}
}
