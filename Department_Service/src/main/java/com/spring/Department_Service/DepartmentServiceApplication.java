package com.spring.Department_Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import com.AOPLogging.aspects.Departmentaop;
import com.splunk.HttpService;
import com.splunk.SSLSecurityProtocol;
import com.splunk.Service;

@SpringBootApplication
@EnableEurekaClient
@Import(Departmentaop.class)
public class DepartmentServiceApplication {
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
				return new RestTemplate();
	}
	

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}
	
	public static Service getService() {
		HttpService.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_2);
		Map<String, Object> connection = new HashMap<String, Object>();
		connection.put("host", "DELL-PC");
		connection.put("username", "tejasplunk");
		connection.put("password", "tejaguna");
		connection.put("port", 8089);
		connection.put("scheme", "https");
		  // Create a Service instance and log in with the argument map
		  Service service = Service.connect(connection);
		  
		return service;
	}


}
