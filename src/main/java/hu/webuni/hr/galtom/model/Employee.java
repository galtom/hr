package hu.webuni.hr.galtom.model;

import java.time.LocalDateTime;


public class Employee {
	private Long id;
	private String name;
	private String position;
	private int salary;
	
	private LocalDateTime startDate;
	
	public Employee() {}
	
	public Employee(Long id, String name, String position, int salary, LocalDateTime startDate) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.startDate = startDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
}
