package hu.webuni.hr.galtom.web;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.galtom.model.Employee;
import hu.webuni.hr.galtom.service.EmployeeService;

@RestController
@RequestMapping("/api/employee/service")
public class EmployeeServiceController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/percent")
	public Map<String, Integer> getSaraly(@RequestBody Employee employee) {
		return Collections.singletonMap("percent", employeeService.getPayRaisePercent(employee));
	}
}
