package sample.lambdas;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Sample {
	public static void main(String args[]) throws Exception {
		System.out.println(executeTask().get());
	}

	private static Future<String> executeTask() throws Exception {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<String> future = executorService.submit(new Callable<String>() {
			public String call() throws Exception {
				return "did something successfully";
			}
		});
		return future;
	}
}
