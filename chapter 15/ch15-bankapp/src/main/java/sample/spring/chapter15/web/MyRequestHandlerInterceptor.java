package sample.spring.chapter15.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyRequestHandlerInterceptor implements HandlerInterceptor {
	private static Logger logger = LogManager.getLogger(MyRequestHandlerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("HTTP method --> " + request.getMethod());
		Enumeration<String> requestNames = request.getParameterNames();
		while (requestNames.hasMoreElements()) {
			String name = requestNames.nextElement();
			String value = request.getParameter(name);
			logger.info("name --> " + name + ", value --> " + value);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("Status code --> " + response.getStatus());
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("Request processing complete");
	}

}
