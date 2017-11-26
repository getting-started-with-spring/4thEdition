package sample.spring.chapter03.bankapp.base;

public class ServiceTemplate {
	private JmsMessageSender jmsMessageSender;
	private EmailMessageSender emailMessageSender;
	private WebServiceInvoker webServiceInvoker;

	public JmsMessageSender getJmsMessageSender() {
		return jmsMessageSender;
	}

	public void setJmsMessageSender(JmsMessageSender jmsMessageSender) {
		this.jmsMessageSender = jmsMessageSender;
	}

	public EmailMessageSender getEmailMessageSender() {
		return emailMessageSender;
	}

	public void setEmailMessageSender(EmailMessageSender emailMessageSender) {
		this.emailMessageSender = emailMessageSender;
	}

	public WebServiceInvoker getWebServiceInvoker() {
		return webServiceInvoker;
	}

	public void setWebServiceInvoker(WebServiceInvoker webServiceInvoker) {
		this.webServiceInvoker = webServiceInvoker;
	}
}
