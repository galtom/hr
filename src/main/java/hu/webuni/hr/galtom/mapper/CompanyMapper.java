package hu.webuni.hr.galtom.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.galtom.dto.CompanyDto;
import hu.webuni.hr.galtom.model.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	List<CompanyDto> companyToDtos(List<Company> companies);
	
	CompanyDto companyToDto(Company company);
	
	Company dtoToCompany(CompanyDto companyDto);
}
