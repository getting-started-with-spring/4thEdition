package sample.reactiveapp.backpressure;

import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.FlowableSubscriber;

public class NumberSubscriber implements FlowableSubscriber<Integer>{
	private static final Logger logger = LoggerFactory
			.getLogger(NumberSubscriber.class);

	private Subscription subscription;
	
	@Override
	public void onNext(Integer t) {
		logger.info("onNext -> " + t);
		
//--uncomment below statements when running NumberPublisherWithBackpressure
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		subscription.request(1);
	}

	@Override
	public void onError(Throwable t) {
		logger.info("onError -> " + t);
		subscription.request(1);
	}

	@Override
	public void onComplete() {
		logger.info("onComplete -> Processing complete");
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		logger.info("onSubscribe -> onSubscribe called");
		this.subscription = subscription;
		subscription.request(1);
	}
}
