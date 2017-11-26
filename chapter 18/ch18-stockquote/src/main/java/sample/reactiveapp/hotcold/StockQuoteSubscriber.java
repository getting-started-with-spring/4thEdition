package sample.reactiveapp.hotcold;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;

public class StockQuoteSubscriber implements FlowableSubscriber<StockQuote> {
	private static Logger logger = LogManager.getLogger(StockQuoteSubscriber.class);
	private final String symbol;
	private final float targetPrice;
	private Subscription subscription;
	private int quantityToSell;
	private String uniqueSubscriberId;

	public StockQuoteSubscriber(String symbol, float targetPrice,
			int quantityToSell, String uniqueSubscriberId) {
		this.uniqueSubscriberId = uniqueSubscriberId;
		this.symbol = symbol;
		this.targetPrice = targetPrice;
		this.quantityToSell = quantityToSell;
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		logger.info(uniqueSubscriberId + ": onSubscribe called for " + symbol);
		this.subscription = subscription;
		subscription.request(1);
	}

	@Override
	public void onNext(StockQuote t) {
		if(t.getSymbol().equalsIgnoreCase(symbol)) {
			logger.info(uniqueSubscriberId + ":" + t.getSymbol() + ": --> "
					+ t.getPrice());
			if(t.getPrice() >= targetPrice) {
				logger.info(uniqueSubscriberId + ":" + "Selling " + quantityToSell + " stocks of "
						+ t.getSymbol() + " at " + t.getPrice());
				subscription.cancel();
			}
		}
		subscription.request(1);
	}

	@Override
	public void onError(Throwable t) {
		logger.info("Error " + t);
	}

	@Override
	public void onComplete() {
		logger.info("Processing complete");
	}
}
