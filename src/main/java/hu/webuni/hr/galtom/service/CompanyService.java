package hu.webuni.hr.galtom.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import hu.webuni.hr.galtom.model.Company;
import hu.webuni.hr.galtom.model.Employee;

@Service
public class CompanyService {

	private Map<Long, Company> companies = new HashMap<>();
	
	{
		companies.put(
				1L,
				new Company(
						1L, 
						"A123456", 
						"Company1",
						"Address of Company1",
						new ArrayList<>(
							Arrays.asList(
								new Employee(1L, "Employee1 of Company1", "IT", 1_000, LocalDateTime.parse("2022-01-01T08:00:00")),
								new Employee(2L, "Employee2 of Company1", "HR", 2_000, LocalDateTime.parse("2021-01-01T08:00:00"))
							)
						)
				)
		);
		companies.put(
				2L, 
				new Company(
						2L, 
						"B987654", 
						"Company2", 
						"Address of Company2",
						new ArrayList<>(
							Arrays.asList(
									new Employee(1L, "Employee1 of Company2", "IT", 1_000, LocalDateTime.parse("2020-01-01T08:00:00")),
									new Employee(2L, "Employee2 of Company2", "HR", 2_000, LocalDateTime.parse("2019-01-01T08:00:00"))
							)
						)
				)
		);
	}
	
	public List<Company> getAll() {
		return new ArrayList<>(companies.values());
	}
	
	public Company findById(long id) {
		return companies.get(id);
	}
	
	public Company save(Company company) {
		companies.put(company.getId(), company);
		return company;
	}
	
	public Company update(Long id, Company company) {
		checkIfCompanyExists(id);
		company.setId(id);
		companies.put(id, company);
		return company;
	}

	public void delete(long id) {
		companies.remove(id);
	}
	
	public Company addEmployee(Long companyId, Employee employee) {
		checkIfCompanyExists(companyId);
		Company company = companies.get(companyId);
		company.getEmployees().add(employee);
		
		return company;
	}
	
	public Company addEmployees(Long companyId, List<Employee> employees) {
		checkIfCompanyExists(companyId);
		
		Company company = companies.get(companyId);
		company.setEmployees(employees);
		
		return company;
	}

	public Company removeEmployee(Long companyId, Long employeeId) {
		checkIfCompanyExists(companyId);
		Company company = companies.get(companyId);
		company.getEmployees().removeIf(emp -> emp.getId() == employeeId);
		
		return company;
	}
	
	private Boolean checkIfCompanyExists(Long id) {
		if (!companies.containsKey(id))
			return null;
		return true;
	}
}
