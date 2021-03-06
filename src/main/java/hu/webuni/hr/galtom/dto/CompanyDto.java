package hu.webuni.hr.galtom.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import hu.webuni.hr.galtom.dto.Views.BaseData;

public class CompanyDto {
	
	@JsonView(BaseData.class)
	private Long id;
	
	@JsonView(BaseData.class)
	private String registrationNumber;
	
	@JsonView(BaseData.class)
	private String name;
	
	@JsonView(BaseData.class)
	private String address;
	
	private List<EmployeeDto> employees;
	
	public CompanyDto() {
	}
	
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
