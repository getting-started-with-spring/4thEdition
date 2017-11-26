package sample.reactiveapp;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

public class HeartbeatPublisher {
	public static void main(String[] args) {
		Flowable.<Integer> create(flowableOnSubscribe,
				BackpressureStrategy.ERROR).subscribe(
				new HeartbeatSubscriber(120));
	}

	public static FlowableOnSubscribe<Integer> flowableOnSubscribe = new FlowableOnSubscribe<Integer>() {
		@Override
		public void subscribe(FlowableEmitter<Integer> emitter)
				throws Exception {
			for (int i = 90; i < 150; i++) {
				emitter.onNext(i);
			}
			emitter.onComplete();
		}
	};
}
