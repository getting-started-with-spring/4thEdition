package sample.reactiveapp.hotcold;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class ColdStockQuotePublisher {
	private static Logger logger = LogManager.getLogger(ColdStockQuotePublisher.class);
	private static Flowable<StockQuote> flowable;

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
				.doAfterNext(t -> Thread.sleep(100))
				.subscribeOn(Schedulers.io(), false);

		logger.info("adding XX subscriber");
		addSubscriber(new StockQuoteSubscriber("XX", 40, 10, "XX subscriber"));

		logger.info("adding YY subscriber");
		addSubscriber(new StockQuoteSubscriber("YY", 100, 10, "YY subscriber"));

		logger.info("adding ZZ subscriber");
		addSubscriberLater(new StockQuoteSubscriber("ZZ", 200, 10, "ZZ subscriber"));

		Thread.sleep(100000);
	}

	public static void addSubscriber(Subscriber<StockQuote> subscriber) {
		flowable.subscribe(subscriber);
	}

	public static void addSubscriberLater(Subscriber<StockQuote> subscriber) {
		new Thread(() -> {
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			addSubscriber(subscriber);
		}).start();
	}
}