package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.Employee;
import com.example.hr.domain.MoneyCurrency;
import com.example.hr.dto.EmployeeRequest;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		TypeMap<EmployeeRequest, Employee> employeeRequestToEmployeeTypeMap = mapper.getTypeMap(EmployeeRequest.class,
				Employee.class);
		if (employeeRequestToEmployeeTypeMap == null) {
			employeeRequestToEmployeeTypeMap = mapper.createTypeMap(EmployeeRequest.class, Employee.class);
		}
		employeeRequestToEmployeeTypeMap.setProvider(provisionRequest -> {
			var employeeRequest = EmployeeRequest.class.cast(provisionRequest.getSource());
			String[] tokens = employeeRequest.getFullname().split("\\w+");
			return new Employee.Builder(employeeRequest.getIdentity()).fullname(tokens[0], tokens[1])
					.iban(employeeRequest.getIban()).salary(employeeRequest.getSalary(), MoneyCurrency.TL)
					.birthYear(employeeRequest.getBirthYear()).fulltime(employeeRequest.isFulltime())
					.department(employeeRequest.getDepartment()).photo(employeeRequest.getPhoto()).build();
		});
		return mapper;
	}
}
