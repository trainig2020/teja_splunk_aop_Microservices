package com.AOPLogging.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.AOPLogging.AopLoggingApplication;
import com.splunk.Receiver;
import com.splunk.Service;

@Aspect
@Component
public class Logging {
	
	Service service = AopLoggingApplication.getService();
	Receiver receiver = service.getReceiver();


	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("within(com.*.controller.*)")
	public void controllerAdvice() {

	}

	
	
	@Pointcut("execution(* com.*.*.*.listDepartment(..))")
	public void listDeptAdvice() {}
	
	@Pointcut("execution(* com.*.*.DeptController.*(..))")
	public void listAllDeptAdvice() {}
	
	@Pointcut("execution(* com.*.*.EmployeeController.*(..))")
	public void listAllEmployeeAdvice() {}
	
	@Pointcut("execution(* com.*.*.*.*(..))")
	public void listAllAdvice() {}



	/*
	 * @Before("controllerAdvice()") public void beforeControllerAdvice(JoinPoint
	 * joinPoint) { System.out.println("Path is " + joinPoint.toString());
	 * log.info("Starting of  URI"); receiver.log("deptemp",
	 * "Client,Before Starting the URI"); }
	 * 
	 * @After("controllerAdvice()") public void afterControllerAdvice(JoinPoint
	 * joinPoint) { receiver.log("deptemp",
	 * "Client,After Executing the URI"+joinPoint.toString()); }
	 */
	
	
	@After("listAllDeptAdvice()")
	public void afterAllDepartment() {
		log.info("Department Advice:All Department related Operations");
		receiver.log("main", "Client,Department Advice:All Department related Operations");
	}
	
	@After("listAllEmployeeAdvice()")
	public void afterAllEmployee() {
		log.info("Employee Advice:All Employee related Operations");
		receiver.log("main", "Client,Employee Advice:All Employee related Operations");
	}

	@Around("controllerAdvice()")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		Object returnvalue = null;
		try {
			System.out.println("Client,Before Around Advice");
			receiver.log("main", "Client,before Around Advice: Before Executing the process");
			returnvalue=proceedingJoinPoint.proceed();
			System.out.println("Client,After  Executing around Advice");
			receiver.log("main", "Client,after Around Advice: After Executing the process "+proceedingJoinPoint.toString());
		}
		catch(Throwable e) {
			System.out.println("Client,After throwing");
			receiver.log("main", "Client,After Throwing an Exception");
		}
		
		return returnvalue;
	}


}
