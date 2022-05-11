package hu.webuni.hr.galtom;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.hr.galtom.model.Employee;
import hu.webuni.hr.galtom.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {
	
	@Autowired
	SalaryService salaryService;
	
	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employee zoltan = new Employee(1L, "Zoltan", "IT", 100_000, LocalDateTime.parse("2017-04-01T08:00:00"));
	
		zoltan.setSalary(salaryService.newSalary(zoltan));
		
		System.out.println(zoltan.getSalary());
	}
}
