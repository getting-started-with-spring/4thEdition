package sample.lambdas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SampleWithLambda {
	public static void main(String args[]) throws Exception {
		System.out.println(executeTask().get());
	}

	private static Future<String> executeTask() throws Exception {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<String> future = executorService.submit(() -> 
			"did something successfully"
		);
		return future;
	}
}
