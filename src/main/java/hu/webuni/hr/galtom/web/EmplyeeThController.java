package hu.webuni.hr.galtom.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.hr.galtom.model.Employee;

@Controller
public class EmplyeeThController {
	
	private List<Employee> employees = new ArrayList<>();
	
	{
	employees.add(new Employee(1L, "Woody", "sheriff", 1_000, LocalDateTime.parse("1996-03-28T08:00:00")));
	employees.add(new Employee(2L, "Buz Lightyear", "Astronaut", 1_000_000, LocalDateTime.parse("1996-03-28T08:00:00")));
	}
	
	@GetMapping("/employees")
	public String index(Map<String, Object> model) {
		 model.put("employees", employees);
		 model.put("newEmployee", new Employee());
		 return "index";
	}
	
	@PostMapping("/employees")
	public String saveEmployee(Employee employee) {
		employees.add(employee);
		return "redirect:/employee";
	}
	
	@GetMapping("/employees/{id}")
	public String getEmployee(Map<String, Object> model, @PathVariable long id) {
		Employee emply = employees.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);		
		model.put("employee", emply);
		return "employee";
	}
	
	@PostMapping("/employees/{id}")
	public String updateEmployee(@PathVariable long id, Employee employee) {
		for(int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getId().equals(id)) {
				employees.set(i, employee);
				break;
			}
		}
		return "redirect:/employee";
	}
	
	@GetMapping("/employees/delete/{id}")
	public String removeEmployee(@PathVariable long id) {
		employees.removeIf(e -> e.getId().equals(id));
		return "redirect:/employee";
	}
}
