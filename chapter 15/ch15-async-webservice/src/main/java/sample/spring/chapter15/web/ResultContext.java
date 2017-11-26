package sample.spring.chapter15.web;

import java.util.Map;

import org.springframework.web.context.request.async.DeferredResult;

public class ResultContext<T> {
	private String methodToInvoke;
	private DeferredResult<T> deferredResult;
	private Map<String, Object> args;
	
	public String getMethodToInvoke() {
		return methodToInvoke;
	}
	public void setMethodToInvoke(String methodToInvoke) {
		this.methodToInvoke = methodToInvoke;
	}
	public DeferredResult<T> getDeferredResult() {
		return deferredResult;
	}
	public void setDeferredResult(DeferredResult<T> deferredResult) {
		this.deferredResult = deferredResult;
	}
	public Map<String, Object> getArgs() {
		return args;
	}
	public void setArgs(Map<String, Object> args) {
		this.args = args;
	}
}
