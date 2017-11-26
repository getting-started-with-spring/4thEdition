package sample.spring.chapter15;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

public class MyErrorHandler extends DefaultResponseErrorHandler {
	private static Logger logger = LogManager.getLogger(MyErrorHandler.class);
	
	@Override
	public void handleError(ClientHttpResponse response)
            throws IOException {
		logger.info("Status code received from the web service : " + response.getStatusCode());
		String body = IOUtils.toString(response.getBody());
		logger.info("Response body: " + body);
		super.handleError(response);
	}
}
