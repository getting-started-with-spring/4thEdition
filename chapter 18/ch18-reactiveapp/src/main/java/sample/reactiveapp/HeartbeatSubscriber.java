package sample.reactiveapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;

public class HeartbeatSubscriber implements FlowableSubscriber<Integer> {
	private static Logger logger = LogManager.getLogger(HeartbeatSubscriber.class);
	private final int targetHeartbeatRate;
	private Subscription subscription;

	public HeartbeatSubscriber(int targetHeartbeatRate) {
		this.targetHeartbeatRate = targetHeartbeatRate;
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
	}

	@Override
	public void onNext(Integer t) {
		logger.info("Heartbeat --> " + t);
		if (t >= targetHeartbeatRate) {
			logger.info("Alert !! " + t);
			subscription.cancel();
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
