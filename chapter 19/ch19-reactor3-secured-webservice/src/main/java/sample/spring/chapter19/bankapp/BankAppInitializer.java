package sample.spring.chapter19.bankapp;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.context.ApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServletHttpHandlerAdapter;
import org.springframework.web.reactive.support.AbstractAnnotationConfigDispatcherHandlerInitializer;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;

import sample.spring.chapter19.bankapp.service.BankAccountServiceImpl;

public class BankAppInitializer extends AbstractAnnotationConfigDispatcherHandlerInitializer {
	public static final String DEFAULT_SERVLET_MAPPING = "/ch19-reactor3-secured-webservice/";
	
	@Override
	protected Class<?>[] getConfigClasses() {
		return new Class[] { WebConfig.class, DatabaseConfig.class, BankAccountServiceImpl.class, SecurityConfig.class };
	}

	//--work around to ensure web request security is enforced when using Spring WebFlux
	//-- refer  https://jira.spring.io/browse/SPR-16144
	protected void registerDispatcherHandler(ServletContext servletContext) {
		String servletName = getServletName();
		ApplicationContext applicationContext = createApplicationContext();

		refreshApplicationContext(applicationContext);
		registerCloseListener(servletContext, applicationContext);

		HttpHandler httpHandler = WebHttpHandlerBuilder.applicationContext(applicationContext).build();
		ServletHttpHandlerAdapter handlerAdapter = new ServletHttpHandlerAdapter(httpHandler);

		ServletRegistration.Dynamic registration = servletContext.addServlet(servletName, handlerAdapter);

		registration.setLoadOnStartup(1);
		registration.addMapping(getServletMapping());
		registration.setAsyncSupported(true);

		customizeRegistration(registration);
	}
}
