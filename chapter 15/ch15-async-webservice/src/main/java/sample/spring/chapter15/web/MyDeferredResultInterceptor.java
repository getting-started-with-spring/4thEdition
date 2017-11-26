package sample.spring.chapter15.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;

public class MyDeferredResultInterceptor implements DeferredResultProcessingInterceptor {
	private static Logger logger = LogManager.getLogger(MyDeferredResultInterceptor.class);
	
	@Override
	public <T> void beforeConcurrentHandling(NativeWebRequest request,
			DeferredResult<T> deferredResult) throws Exception {
		logger.info("beforeConcurrentHandling");
	}

	@Override
	public <T> void preProcess(NativeWebRequest request,
			DeferredResult<T> deferredResult) throws Exception {
		logger.info("preProcess");	
	}

	@Override
	public <T> void postProcess(NativeWebRequest request,
			DeferredResult<T> deferredResult, Object concurrentResult)
			throws Exception {
		logger.info("postProcess");	
	}

	@Override
	public <T> boolean handleTimeout(NativeWebRequest request,
			DeferredResult<T> deferredResult) throws Exception {
		logger.info("handleTimeout");	
		return false;
	}

	@Override
	public <T> void afterCompletion(NativeWebRequest request,
			DeferredResult<T> deferredResult) throws Exception {
		logger.info("afterCompletion");	
	}
}
