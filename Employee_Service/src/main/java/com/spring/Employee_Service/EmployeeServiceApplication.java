package com.spring.Employee_Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

import com.AOPLogging.aspects.Employeeaop;

import com.splunk.HttpService;
import com.splunk.SSLSecurityProtocol;
import com.splunk.Service;

@SpringBootApplication
@EnableEurekaClient
@Import(Employeeaop.class)
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}
	
	public static Service getService() {
		HttpService.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_2);
		Map<String, Object> connection = new HashMap<String, Object>();
		connection.put("host", "localhost");
		connection.put("username", "tejasplunk");
		connection.put("password", "tejaguna");
		connection.put("port", 8089);
		connection.put("scheme", "https");
		  // Create a Service instance and log in with the argument map
		  Service service = Service.connect(connection);
		    
		 
		return service;
	}


}
