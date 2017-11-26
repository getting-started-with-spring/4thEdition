package sample.spring.chapter11.bankapp.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private Logger logger = LogManager.getLogger(LoggingAspect.class);

	@Before(value = "execution(* sample.spring.chapter11.bankapp.service.*Service.*(..))")
	public void log(JoinPoint joinPoint) {
		logger.info("Entering "
				+ joinPoint.getTarget().getClass().getSimpleName() + "'s "
				+ joinPoint.getSignature().getName());
		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			logger.info("args[" + i + "] -->" + args[i]);
		}
	}
}
