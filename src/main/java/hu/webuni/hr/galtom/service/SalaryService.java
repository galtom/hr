package hu.webuni.hr.galtom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.galtom.model.Employee;

@Service
public class SalaryService {
		
	@Autowired
	EmployeeService employeeService;
	
	public int newSalary(Employee employee) {
		return (int)(employee.getSalary() * ((100 + employeeService.getPayRaisePercent(employee)) / 100.0));
	}
}
