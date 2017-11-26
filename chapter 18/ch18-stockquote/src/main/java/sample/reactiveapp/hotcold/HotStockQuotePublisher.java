package sample.reactiveapp.hotcold;

import io.reactivex.Flowable;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HotStockQuotePublisher {
	private static Logger logger = LogManager.getLogger(HotStockQuotePublisher.class);
	private static ConnectableFlowable<StockQuote> flowable;

	public static void main(String[] args) throws InterruptedException {
		List<StockQuote> stockQuoteList = new ArrayList<StockQuote>();

		for (int i = 0; i < 100; i++) {
			stockQuoteList.add(StockQuoteSupplier.getStockQuote("XX"));
			stockQuoteList.add(StockQuoteSupplier.getStockQuote("YY"));
			stockQuoteList.add(StockQuoteSupplier.getStockQuote("ZZ"));
		}
		
		//-- add .doOnEach(t -> logger.info("Emitting data")) to print
		// 'Emitting data' each time an item is emitted
		
		flowable = Flowable.fromIterable(stockQuoteList)
				.doAfterNext((t) -> Thread.sleep(100))
				.subscribeOn(Schedulers.io(), false).publish();

		logger.info("adding XX subscriber");
		addSubscriber("XX", 40, 10, "XX subscriber");

		logger.info("adding YY subscriber");
		addSubscriber("YY", 100, 10, "YY subscriber");

		logger.info("adding ZZ subscriber");
		addSubscriberLater("ZZ", 200, 10, "ZZ subscriber");

		flowable.connect();
		Thread.sleep(100000);
	}

	public static void addSubscriber(String symbol, float targetPrice,
			int quantityToSell, String uniqueSubscriberId) {
		flowable.subscribe(new StockQuoteSubscriber(symbol, targetPrice,
				quantityToSell, uniqueSubscriberId));
	}

	public static void addSubscriberLater(String symbol, float targetPrice,
			int quantityToSell, String uniqueSubscriberId) {
		new Thread(() -> {
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			addSubscriber(symbol, targetPrice, quantityToSell,
					uniqueSubscriberId);
		}).start();
	}
}
