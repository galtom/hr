package hu.webuni.hr.galtom.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hu.webuni.hr.galtom.model.Employee;

@Controller
public class EmplyeeThController {
	
	private List<Employee> employees = new ArrayList<>();
	
	{
		employees.add(new Employee(1L, "Woody", "sheriff", 1_000, LocalDateTime.parse("1996-03-28T08:00:00")));
		employees.add(new Employee(2L, "Buz Lightyear", "Astronaut", 1_000_000, LocalDateTime.parse("1996-03-28T08:00:00")));
	}
	
	@GetMapping("/")
	public String index(Map<String, Object> model) {
		 model.put("employees", employees);
		 model.put("newEmployee", new Employee());
		 return "index";
	}
	
	@PostMapping("/")
	public String saveEmployee(Employee employee) {
		employees.add(employee);
		return "redirect:/";
	}
}
