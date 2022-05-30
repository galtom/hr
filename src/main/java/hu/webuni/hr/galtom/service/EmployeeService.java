package hu.webuni.hr.galtom.service;

import java.util.List;

import hu.webuni.hr.galtom.model.Employee;

public interface EmployeeService {
	public int getPayRaisePercent(Employee employee);

	public Employee findById(Long id);

	public Employee updateEmployee(Long id, Employee dtoToEmployee);

	public void removeEmployee(Long id);

	public Employee save(Employee dtoToEmployee);

	public List<Employee> getAll(Integer salary);
}
