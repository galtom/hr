package hu.webuni.hr.galtom.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.hr.galtom.dto.EmployeeDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {
	
	private static final String BASE_URI = "/api/employees";
	
	@Autowired
	WebTestClient webTestClient;
	
	@Test
	void testThatEmployeeUpdated() throws Exception {
		EmployeeDto employeeBeforeUpdate = getEmployee(1);
		EmployeeDto newEmployee = new EmployeeDto(
				employeeBeforeUpdate.getId(),
				employeeBeforeUpdate.getName(),
				"HR",
				10_000,
				employeeBeforeUpdate.getStartDate());
		
		updateEmployee(newEmployee);
		EmployeeDto employeeAfterUpdate = getEmployee(1);
		
		assertThat(newEmployee).usingRecursiveComparison().isEqualTo(employeeAfterUpdate);
	}
	
	@Test
	void testThatNewEmployeeAdded() throws Exception {
		EmployeeDto newEmployee = new EmployeeDto(5L, "Fifth Employee", "Assistant", 2_000, LocalDateTime.parse("2020-03-28T08:00:00"));
		newEmployee(newEmployee);
		
		EmployeeDto savedNewEmployee = getEmployee(newEmployee.getId().intValue());
		
		assertThat(newEmployee).usingRecursiveComparison().isEqualTo(savedNewEmployee);
	}
	
	@Test
	void testThatEmplyEmpoloyeeNameShouldResultBadRequest() throws Exception {
		EmployeeDto newEmployee = new EmployeeDto(5L, "", "Assistant", 2_000, LocalDateTime.parse("2020-03-28T08:00:00"));
		
		webTestClient.post()
			.uri(BASE_URI)
			.bodyValue(newEmployee)
			.exchange()
			.expectStatus().isBadRequest();
	}
	
	@Test
	void testThatOnEmployeeUpdateNegativeSalaryShouldResultBadRequest() throws Exception {
		EmployeeDto employee = getEmployee(1);
		EmployeeDto newEmployee = new EmployeeDto(
				employee.getId(),
				employee.getName(),
				employee.getPosition(),
				-10,
				employee.getStartDate());
		
		webTestClient
		.put()
		.uri(uriBuild -> uriBuild
				.path(BASE_URI + "/{id}")
				.build(newEmployee.getId()))
		.bodyValue(newEmployee)
		.exchange()
		.expectStatus()
		.isBadRequest();
	}

	private void updateEmployee(EmployeeDto employee) {
		webTestClient
			.put()
			.uri(uriBuild -> uriBuild
					.path(BASE_URI + "/{id}")
					.build(employee.getId()))
			.bodyValue(employee)
			.exchange()
			.expectStatus()
			.isOk();
	}
	
	private void newEmployee(EmployeeDto employee) {
		webTestClient
			.post()
			.uri(BASE_URI)
			.bodyValue(employee)
			.exchange()
			.expectStatus()
			.isOk();
	}

	private EmployeeDto getEmployee(int id) {
		EmployeeDto employee = webTestClient
			.get()
			.uri(uriBuilder -> uriBuilder
					.path(BASE_URI + "/{id}")
					.build(id))
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody(EmployeeDto.class)
			.returnResult()
			.getResponseBody();
		return employee;
	}

}
