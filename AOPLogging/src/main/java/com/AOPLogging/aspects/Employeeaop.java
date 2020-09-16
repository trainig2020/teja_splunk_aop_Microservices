package com.AOPLogging.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.AOPLogging.AopLoggingApplication;
import com.splunk.Receiver;
import com.splunk.Service;


@Aspect
@Component
public class Employeeaop {
	Service service = AopLoggingApplication.getService();
	Receiver receiver = service.getReceiver();


	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("within(com.*.*.controller.*)")
	public void controllerAdvice() {

	}

	@Pointcut("within(com.*.*.service.*)" + "|| within(com.*.*.dao.*)")
	public void servicesAdvice() {

	}

	@Pointcut("execution(* com.*.*.*.*.getEmpByDeptId(..))")
	public void listEmpAdvice() {
	}

	

	@Pointcut("execution(* com.*.*.*.*.*(..))")
	public void listAllAdvice() {
	}

	@Before("listAllAdvice()")
	public void beforeAdvice() {
		System.out.println("Employee...Before All crud");
	}

	

	@After("servicesAdvice()")
	public void afterControllerAdvice(JoinPoint joinPoint) {
		log.info(" Completion of  Service , dao layers"+joinPoint.toString());
		receiver.log("main", "Employee,Completion of  Service , dao"+joinPoint.toString());
	}

	

	@Around("listEmpAdvice()")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		Object returnvalue = null;
		try {
			System.out.println("Before Employee Around Advice");
			receiver.log("main", "Employee..Before  Employee Operation");
			returnvalue = proceedingJoinPoint.proceed();
			System.out.println("After returning Employee around Advice");
			receiver.log("main", "Employee..After Execution of  Employe "+proceedingJoinPoint.toString());
		} catch (Throwable e) {
			System.out.println("After throwing");
			receiver.log("main", "Employee:After Throwing an Exception");
		}
		
		return returnvalue;
	}

}
