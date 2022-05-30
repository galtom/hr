package hu.webuni.hr.galtom.web;

import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonView;

import hu.webuni.hr.galtom.dto.CompanyDto;
import hu.webuni.hr.galtom.dto.EmployeeDto;
import hu.webuni.hr.galtom.dto.Views;
import hu.webuni.hr.galtom.mapper.CompanyMapper;
import hu.webuni.hr.galtom.mapper.EmployeeMapper;
import hu.webuni.hr.galtom.model.Company;
import hu.webuni.hr.galtom.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	CompanyMapper companyMapper;

	@GetMapping(params = "full=true")
	public List<CompanyDto> getAll() {
		return companyMapper.companyToDtos(companyService.getAll());
	}
	
	@GetMapping
	@JsonView(Views.BaseData.class)
	public List<CompanyDto> getAllWithoutEmployees(@RequestParam(required = false, defaultValue = "false") Boolean full){
		return companyMapper.companyToDtos(companyService.getAll());
	}
	
	@GetMapping(path = "/{id}", params = "full=true")
	public CompanyDto findById(@PathVariable long id) {
		return companyMapper.companyToDto(getCompanyById(id));
	}
	
	@GetMapping("/{id}")
	@JsonView(Views.BaseData.class)
	public CompanyDto findByIdWithoutEmployees(
			@PathVariable long id, 
			@RequestParam(required = false, defaultValue = "false") Boolean full) {
		return companyMapper.companyToDto(getCompanyById(id));
	}

	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		companyService.save(companyMapper.dtoToCompany(companyDto));
		return companyDto;
	}

	@PutMapping("/{id}")
	public CompanyDto modifyCompany(
			@PathVariable long id,
			@RequestBody CompanyDto companyDto) {
		Company company = companyService.update(id, companyMapper.dtoToCompany(companyDto));
		raiseNotFoundIfObjectIsNull(company);
		
		return companyMapper.companyToDto(company);
	}

	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companyService.delete(id);
	}

	@PostMapping("/{id}/employees")
	public CompanyDto addNewEmployee(
			@PathVariable long id,
			@RequestBody EmployeeDto employeeDto) {
		Company company = companyService.addEmployee(id, employeeMapper.dtoToEmployee(employeeDto));
		raiseNotFoundIfObjectIsNull(company);
		
		return companyMapper.companyToDto(company);
	}

	@DeleteMapping("/{id}/employees/{employeeId}")
	public CompanyDto deleteEmployee(
			@PathVariable long id,
			@PathVariable long employeeId) {
		Company company = companyService.removeEmployee(id, employeeId);
		raiseNotFoundIfObjectIsNull(company);
		
		return companyMapper.companyToDto(company);
	}

	@PutMapping("/{id}/employees")
	public CompanyDto addNewEmployee(
			@PathVariable long id,
			@RequestBody List<EmployeeDto> employees) {
		Company company = companyService.addEmployees(id, employeeMapper.dtosToEmployee(employees));
		raiseNotFoundIfObjectIsNull(company);
		
		return companyMapper.companyToDto(company);
	}
	
	private void raiseNotFoundIfObjectIsNull(Object object) {
		if (object == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	private Company getCompanyById(long id) {
		Company company = companyService.findById(id);
		raiseNotFoundIfObjectIsNull(company);
		
		return company;
	}
}
