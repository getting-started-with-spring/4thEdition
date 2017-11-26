package sample.spring.chapter10.bankapp.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class EmailMessageListener implements MessageListener {
	private static Logger logger = LogManager.getLogger(EmailMessageListener.class);
			
	@Autowired
	private transient MailSender mailSender;

	@Autowired
	@Qualifier("requestReceivedTemplate")
	private transient SimpleMailMessage simpleMailMessage;

	public void sendEmail() {
		mailSender.send(simpleMailMessage);
	}

	@Override
	public void onMessage(Message message) {
		logger.info("EmailMessageListener's onMessage() invoked");
		TextMessage textMessage = (TextMessage) message;
		try {
			simpleMailMessage.setTo(textMessage.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		sendEmail();
	}
}