package hu.webuni.hr.galtom.service;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;

import hu.webuni.hr.galtom.config.HrConfigProperties;
import hu.webuni.hr.galtom.model.Employee;

public class SmartEmployeeService extends AbstractEmployeeService {
	
	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {
		int percent = 0;
		LocalDateTime now = LocalDateTime.now();
		long employeeMonths = employee.getStartDate().until(now, ChronoUnit.MONTHS);
		
		long previousSavedMonth = 0;
		
		for(int i=0; i < config.getSalary().getPayRaise().size(); i++) {
			long month = (int)(config.getSalary().getPayRaise().get(i).getYears() * 12.0);
			
			if (employeeMonths >= month  && month >= previousSavedMonth) {
				percent = config.getSalary().getPayRaise().get(i).getPercent();
				previousSavedMonth = month;
			}
		}
		
		return percent;
	}
}
