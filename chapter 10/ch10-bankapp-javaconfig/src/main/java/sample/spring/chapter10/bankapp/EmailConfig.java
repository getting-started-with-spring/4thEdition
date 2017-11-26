package sample.spring.chapter10.bankapp;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:/META-INF/spring/email.properties")
@PropertySource("classpath:/META-INF/spring/emailtemplate.properties")
public class EmailConfig {
	@Value("${email.host}")
	private String host;

	@Value("${email.port}")
	private int port;

	@Value("${email.protocol}")
	private String protocol;

	@Value("${email.username}")
	private String username;

	@Value("${email.password}")
	private String password;

	@Value("${email.text.request.received}")
	private String requestReceivedText;

	@Value("${email.subject.request.received}")
	private String requestReceivedSubject;

	@Value("${email.text.request.processed}")
	private String requestProcessedText;

	@Value("${email.subject.request.processed}")
	private String requestProcessedSubject;

	@Bean(name = "mailSender")
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost(host);
		javaMailSenderImpl.setProtocol(protocol);
		javaMailSenderImpl.setUsername(username);
		javaMailSenderImpl.setPassword(password);
		javaMailSenderImpl.setPort(port);
		
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailSenderImpl.setJavaMailProperties(javaMailProperties);
		return javaMailSenderImpl;
	}

	@Bean(name = "requestReceivedTemplate")
	public SimpleMailMessage requestReceivedTemplate() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setText(requestReceivedText);
		message.setSubject(requestReceivedSubject);
		return message;
	}

	@Bean(name = "requestProcessedTemplate")
	public SimpleMailMessage requestProcessedTemplate() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setText(requestProcessedText);
		message.setSubject(requestProcessedSubject);
		return message;
	}
}
