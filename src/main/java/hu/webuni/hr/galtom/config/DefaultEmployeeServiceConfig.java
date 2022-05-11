package hu.webuni.hr.galtom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.galtom.service.DefaultEmployeeService;
import hu.webuni.hr.galtom.service.EmployeeService;

@Configuration
@Profile("!smart")
public class DefaultEmployeeServiceConfig {
	
	@Bean
	public EmployeeService employeeService() {
		return new DefaultEmployeeService();
	}
}
