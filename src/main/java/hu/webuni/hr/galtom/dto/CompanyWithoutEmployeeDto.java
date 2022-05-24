package hu.webuni.hr.galtom.dto;

public class CompanyWithoutEmployeeDto {
	private Long id;
	private String registrationNumber;
	private String name;
	private String address;
	
	public CompanyWithoutEmployeeDto() {
	}
	
	public CompanyWithoutEmployeeDto(CompanyDto c) {
		this.id = c.getId();
		this.registrationNumber = c.getRegistrationNumber();
		this.name = c.getName();
		this.address = c.getAddress();
	}
	
	public CompanyWithoutEmployeeDto(Long id, String registrationNumber, String name, String address) {
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.address = address;
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
	
	
	
}
