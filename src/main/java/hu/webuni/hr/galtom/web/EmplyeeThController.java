package hu.webuni.hr.galtom.web;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.hr.galtom.model.Employee;

@Controller
public class EmplyeeThController {
	
	private Map<Long, Employee> employees = new HashMap<>();
	
	{
		employees.put(1L, new Employee(1L, "Woody", "sheriff", 1_000, LocalDateTime.parse("1996-03-28T08:00:00")));
		employees.put(2L, new Employee(2L, "Buz Lightyear", "Astronaut", 1_000_000, LocalDateTime.parse("1996-03-28T08:00:00")));
	}
	
	@GetMapping("/")
	public String index(Map<String, Object> model) {
		 model.put("employees", employees.values());
		 model.put("newEmployee", new Employee());
		 return "index";
	}
	
	@PostMapping("/")
	public String saveEmployee(Employee employee) {
		employees.put(employee.getId(), employee);
		return "redirect:/";
	}
	
	@GetMapping("/employee/{id}")
	public String getEmployee(Map<String, Object> model, @PathVariable long id) {
		Employee emply = employees.get(id);		
		model.put("employee", emply);
		return "employee";
	}
	
	@PostMapping("/employee/{id}")
	public String updateEmployee(@PathVariable long id, Employee employee) {
		employees.put(employee.getId(), employee);
		return "redirect:/";
	}
	
	@GetMapping("/employee/delete/{id}")
	public String removeEmployee(@PathVariable long id) {
		employees.remove(id);
		return "redirect:/";
	}
}
