package hu.webuni.hr.galtom.service;

import hu.webuni.hr.galtom.model.Employee;

public class DefaultEmployeeService extends AbstractEmployeeService {

	@Override
	public int getPayRaisePercent(Employee employee) {
		return 5;
	}

}
