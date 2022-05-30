package hu.webuni.hr.galtom.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import hu.webuni.hr.galtom.model.Employee;

public abstract class AbstractEmployeeService implements EmployeeService {
	
	private Map<Long, Employee> employees = new HashMap<>();
	
	{
		employees.put(1L, new Employee(1L, "Woody", "sheriff", 1_000, LocalDateTime.parse("1996-03-28T08:00:00")));
		employees.put(2L, new Employee(2L, "Buz Lightyear", "Astronaut", 1_000_000, LocalDateTime.parse("1996-03-28T08:00:00")));
	}
	
	private List<Employee> filterSaralyIfMoreThanWithStream(int salary) {
		return employees.values().stream().filter(emply -> emply.getSalary() > salary).collect(Collectors.toList());
	}
	
	public List<Employee> getAll(Integer salary) {
		if (salary != null)
			return filterSaralyIfMoreThanWithStream(salary);
			
		return new ArrayList<>(employees.values());
	}
	
	public Employee findById(Long id) {
		return employees.get(id);
	}
	
	public Employee save(Employee employee) {
		employees.put(employee.getId(), employee);
		return employee;
	}
	
	public Employee updateEmployee(Long id, Employee employee) {
		if (!employees.containsKey(id))
			return null;
		
		employee.setId(id);
		employees.put(id, employee);
		return employee;
	}

	public void removeEmployee(Long id) {
		employees.remove(id);
	}
}
