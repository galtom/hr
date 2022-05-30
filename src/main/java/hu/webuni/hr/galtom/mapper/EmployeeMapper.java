package hu.webuni.hr.galtom.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.galtom.dto.EmployeeDto;
import hu.webuni.hr.galtom.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	List<EmployeeDto> employeeToDtos(List<Employee> employees);
	
	EmployeeDto employeeToDto(Employee employee);
	
	Employee dtoToEmployee(EmployeeDto employeeDto);
	
	List<Employee> dtosToEmployee(List<EmployeeDto> employeeDtos);
}
