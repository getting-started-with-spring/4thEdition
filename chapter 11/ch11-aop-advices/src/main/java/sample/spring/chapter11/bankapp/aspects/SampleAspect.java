package sample.spring.chapter11.bankapp.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import sample.spring.chapter11.bankapp.dao.FixedDepositDao;
import sample.spring.chapter11.bankapp.domain.FixedDepositDetails;

@Aspect
@Component
@SuppressWarnings("unused")
public class SampleAspect {
	private Logger logger = LogManager.getLogger(SampleAspect.class);

	@Pointcut(value = "execution(* sample.spring..BankAccountService.createBankAccount(..))")
	private void createBankAccountMethod() {

	}

	@Pointcut(value = "execution(* sample.spring..FixedDepositService.*(..))")
	private void exceptionMethods() {

	}

	@AfterReturning(value = "createBankAccountMethod()", returning = "aValue")
	public void afterReturningAdvice(JoinPoint joinPoint, int aValue) {
		logger.info("Value returned by " + joinPoint.getSignature().getName()
				+ " method is " + aValue);
	}

	@AfterThrowing(value = "exceptionMethods()", throwing = "exception")
	public void afterThrowingAdvice(JoinPoint joinPoint, Throwable exception) {
		logger.info("Exception thrown by " + joinPoint.getSignature().getName()
				+ " Exception type is : " + exception);
	}

	@After(value = "exceptionMethods() || createBankAccountMethod()")
	public void afterAdvice(JoinPoint joinPoint) {
		logger.info("After advice executed for "
				+ joinPoint.getSignature().getName());
	}

	@Around(value = "execution(* sample.spring..*Service.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) {
		Object obj = null;
		StopWatch watch = new StopWatch();
		watch.start();
		try {
			obj = pjp.proceed();
		} catch (Throwable throwable) {
			// -- perform any action that you want
		}
		watch.stop();
		logger.info(watch.prettyPrint());
		return obj;
	}
}
