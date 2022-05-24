package hu.webuni.hr.galtom.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.galtom.dto.CompanyDto;
import hu.webuni.hr.galtom.dto.CompanyWithoutEmployeeDto;
import hu.webuni.hr.galtom.dto.EmployeeDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	
	private Map<Long, CompanyDto> companies = new HashMap<>();
	
	{
		companies.put(
				1L,
				new CompanyDto(
						1L, 
						"A123456", 
						"Company1",
						"Address of Company1",
						new ArrayList<>(
							Arrays.asList(
								new EmployeeDto(1L, "Employee1 of Company1", "IT", 1_000, LocalDateTime.parse("2022-01-01T08:00:00")),
								new EmployeeDto(2L, "Employee2 of Company1", "HR", 2_000, LocalDateTime.parse("2021-01-01T08:00:00"))
							)
						)
				)
		);
		companies.put(
				2L, 
				new CompanyDto(
						2L, 
						"B987654", 
						"Company2", 
						"Address of Company2",
						new ArrayList<>(
							Arrays.asList(
									new EmployeeDto(1L, "Employee1 of Company2", "IT", 1_000, LocalDateTime.parse("2020-01-01T08:00:00")),
									new EmployeeDto(2L, "Employee2 of Company2", "HR", 2_000, LocalDateTime.parse("2019-01-01T08:00:00"))
							)
						)
				)
		);
	}

	@GetMapping
	public List<Object> getAll(@RequestParam(name = "full", defaultValue = "false", required = false) boolean full) {
		
		if (full)
			return new ArrayList<>(companies.values());
		
		List<Object> listOfCompaniesWithoutEmployee = new ArrayList<Object>();
		
		for(CompanyDto companyDto: companies.values()) {
			listOfCompaniesWithoutEmployee.add(new CompanyWithoutEmployeeDto(companyDto));
		}

		return listOfCompaniesWithoutEmployee;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOne(@RequestParam(name = "full", defaultValue = "false", required = false) boolean full, @PathVariable long id) {
		CompanyDto company = companies.get(id);
		
		if (company == null)
			return ResponseEntity.notFound().build();
		
		if (full)
			return ResponseEntity.ok(company);
		
		return ResponseEntity.ok(new CompanyWithoutEmployeeDto(company));
	}
	
	@PostMapping
	public ResponseEntity<CompanyDto> addCompany(@RequestBody CompanyDto companyDto) {
		if (companies.containsKey(companyDto.getId()))
			return ResponseEntity.badRequest().build();
		
		companies.put(companyDto.getId(), companyDto);
		
		return ResponseEntity.ok(companyDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> updateCompany(
			@PathVariable Long id,
			@RequestBody CompanyDto companyDto) {
		
		if (!companies.containsKey(id))
			return ResponseEntity.notFound().build();
		
		companyDto.setId(id);
		companies.put(id, companyDto);
		return ResponseEntity.ok(companyDto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable Long id) {
		companies.remove(id);
	}
	
	@PostMapping("/{companyId}/employees")
	public EmployeeDto newEmployee(
			@PathVariable long companyId,
			@RequestBody EmployeeDto employeeDto) {
		
		companies.get(companyId).getEmployees().add(employeeDto);
		
		return employeeDto;
	}
	
	@DeleteMapping("/{companyId}/employees/{employeeId}")
	public void removeEmployee(
			@PathVariable long companyId,
			@PathVariable long employeeId) {
		
		companies.get(companyId).getEmployees().removeIf(e -> e.getId() == employeeId);
	}
	
	@PutMapping("/{companyId}/employees")
	public ResponseEntity<List<EmployeeDto>> updateEmployees(
			@PathVariable Long companyId,
			@RequestBody List<EmployeeDto> employees) {
		
		companies.get(companyId).setEmployees(employees);
		
		return ResponseEntity.ok(employees);
	}
}
