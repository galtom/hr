package hu.webuni.hr.galtom.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.galtom.dto.EmployeeDto;
import hu.webuni.hr.galtom.mapper.EmployeeMapper;
import hu.webuni.hr.galtom.model.Employee;
import hu.webuni.hr.galtom.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeSrvc;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@GetMapping
	public List<EmployeeDto> getAll(@RequestParam(name = "salary", required = false, defaultValue = "") Integer salary) {
		return employeeMapper.employeeToDtos(employeeSrvc.getAll(salary));
	}
	
	@GetMapping("/{id}")
	public EmployeeDto getEmployee(@PathVariable Long id) {
		Employee employee = employeeSrvc.findById(id);
		
		if (employee == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		return employeeMapper.employeeToDto(employee);
	}
	
	@PostMapping
	public EmployeeDto addEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		employeeSrvc.save(employeeMapper.dtoToEmployee(employeeDto));
		return employeeDto;
	}
	
	@PutMapping("/{id}")
	public EmployeeDto updateEmployee(
			@PathVariable Long id,
			@RequestBody @Valid EmployeeDto employeeDto) {
		
		Employee employee = employeeSrvc.updateEmployee(id, employeeMapper.dtoToEmployee(employeeDto));
		
		if (employee == null )
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		return employeeMapper.employeeToDto(employee);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		employeeSrvc.removeEmployee(id);
	}	
}
