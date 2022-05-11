package hu.webuni.hr.galtom.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import hu.webuni.hr.galtom.dto.EmployeeDto;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	private Map<Long, EmployeeDto> employees = new HashMap<>();
	
	{
		employees.put(1L, new EmployeeDto(1L, "Woody", "sheriff", 1_000, LocalDateTime.parse("1996-03-28T08:00:00")));
		employees.put(2L, new EmployeeDto(2L, "Buz Lightyear", "Astronaut", 1_000_000, LocalDateTime.parse("1996-03-28T08:00:00")));
	}
	
	private List<EmployeeDto> filterSaralyIfMoreThan(int salary) {
		List<EmployeeDto> filteredEmployees = new ArrayList<>();
		
		for (EmployeeDto employeeDto : employees.values()) {
			if (employeeDto.getSalary() > salary)
				filteredEmployees.add(employeeDto);
		}
		
		return filteredEmployees;
	}
	
	@GetMapping
	public List<EmployeeDto> getAll(@RequestParam(name = "salary", required = false) Integer salary) {
		if (salary != null)
			return filterSaralyIfMoreThan(salary);
			
		return new ArrayList<>(employees.values());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id) {
		EmployeeDto employee = employees.get(id);
		
		if (employee == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(employee);
	}
	
	@PostMapping
	public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto) {
		employees.put(employeeDto.getId(), employeeDto);
		return employeeDto;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(
			@PathVariable Long id,
			@RequestBody EmployeeDto employeeDto) {
		
		if (!employees.containsKey(id))
			return ResponseEntity.notFound().build();
		
		employeeDto.setId(id);
		employees.put(id, employeeDto);
		return ResponseEntity.ok(employeeDto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		employees.remove(id);
	}	
}
