package hu.webuni.hr.galtom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.galtom.service.EmployeeService;
import hu.webuni.hr.galtom.service.SmartEmployeeService;

@Configuration
@Profile("smart")
public class SmartEmployeeServiceConfig {
	
	@Bean
	public EmployeeService employeeService() {
		return new SmartEmployeeService();
	}
}
