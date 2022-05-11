package hu.webuni.hr.galtom.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.galtom.model.Employee;

@Service
public class DefaultEmployeeService implements EmployeeService {

	@Override
	public int getPayRaisePercent(Employee employee) {
		return 5;
	}

}
