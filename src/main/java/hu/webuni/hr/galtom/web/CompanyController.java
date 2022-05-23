package hu.webuni.hr.galtom.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.galtom.dto.CompanyDto;
import hu.webuni.hr.galtom.dto.EmployeeDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	
	private Map<Long, CompanyDto> companies = new HashMap<>();
	
	{
		companies.put(
				1L,
				new CompanyDto(1L, "A123456", "Company1", "Address of Company1", Arrays.asList(
						new EmployeeDto(1L, "Employee1 of Company1", "IT", 1_000, LocalDateTime.parse("2022-01-01T08:00:00")),
						new EmployeeDto(2L, "Employee2 of Company1", "HR", 2_000, LocalDateTime.parse("2021-01-01T08:00:00"))
				))
		);
		companies.put(
				2L, 
				new CompanyDto(2L, "B987654", "Company2", "Address of Company2", Arrays.asList(
						new EmployeeDto(1L, "Employee1 of Company2", "IT", 1_000, LocalDateTime.parse("2020-01-01T08:00:00")),
						new EmployeeDto(2L, "Employee2 of Company2", "HR", 2_000, LocalDateTime.parse("2019-01-01T08:00:00"))
				))
		);
	}
	
	@GetMapping
	public List<CompanyDto> getAll() {
		return new ArrayList<>(companies.values());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getOne(@PathVariable long id) {
		CompanyDto company = companies.get(id);
		
		if (company == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(company);
	}
	
	
}
