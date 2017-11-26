package sample.reactiveapp.backpressure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class NumberPublisherDropOnBackpressure {
	private static final Logger logger = LogManager
			.getLogger(NumberPublisherDropOnBackpressure.class);

	public static void main(String args[]) throws InterruptedException {
		Flowable.create(flowableOnSubscribe, BackpressureStrategy.MISSING)
				.subscribeOn(Schedulers.computation())
				.doOnEach(t -> Thread.sleep(50))
				.onBackpressureDrop(
						t -> logger.info("---------------> Dropped " + t))
				.observeOn(Schedulers.computation(), true)
				.filter(t -> t % 2 == 0).subscribe(new NumberSubscriber());
		Thread.sleep(100000);
	}

	private static FlowableOnSubscribe<Integer> flowableOnSubscribe = new FlowableOnSubscribe<Integer>() {
		public void subscribe(FlowableEmitter<Integer> emitter)
				throws InterruptedException {
			logger.info("FlowableOnSubscriber's subscribe method called");
			int count = 1;
			while (count < Integer.MAX_VALUE) {
				emitter.onNext(count++);
			}
		}
	};
}
