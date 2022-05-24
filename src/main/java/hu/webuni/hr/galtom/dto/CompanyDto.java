package hu.webuni.hr.galtom.dto;

import java.util.List;

public class CompanyDto {
	
	private Long id;
	private String registrationNumber;
	private String name;
	private String address;
	private List<EmployeeDto> employees;
	
	public CompanyDto() {
	}
	
//	public CompanyDto(CompanyDto c) {
//		this.id = c.id;
//		this.registrationNumber = c.registrationNumber;
//		this.name = c.name;
//		this.address = c.address;
//		this.employees = c.employees;
//	}
	
	public CompanyDto(Long id, String registrationNumber, String name, String address) {
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.address = address;
	}
	
	public CompanyDto(Long id, String registrationNumber, String name, String address, List<EmployeeDto> employees) {
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.address = address;
		this.employees = employees;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public List<EmployeeDto> getEmployees() {
		return employees;
	}
	
	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}
}
