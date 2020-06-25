package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;
import com.example.hr.domain.FullName;
import com.example.hr.domain.MoneyCurrency;
import com.example.hr.dto.EmployeeRequest;
import com.example.hr.orm.EmployeeEntity;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Configuration
public class ModelMapperConfig {
	private static final Converter<EmployeeDocument, Employee> employeeDocumentToEmployeeConverter = context -> {
		var employeeDocument = context.getSource();
		String[] tokens = employeeDocument.getFullname().split("\\w+");
		return new Employee.Builder(employeeDocument.getIdentity()).fullname(tokens[0], tokens[1])
				.iban(employeeDocument.getIban()).salary(employeeDocument.getSalary(), MoneyCurrency.TL)
				.birthYear(employeeDocument.getBirthYear()).fulltime(employeeDocument.getFulltime())
				.department(employeeDocument.getDepartment()).photo(employeeDocument.getPhoto().getBytes()).build();
	};
	private static final Converter<Employee, EmployeeDocument> employeeToEmployeeDocumentConverter = (context -> {
		var employee = context.getSource();
		EmployeeDocument employeeDocument = new EmployeeDocument();
		employeeDocument.setIdentity(employee.getIdentityNo().getValue());
		FullName fullname = employee.getFullname();
		employeeDocument.setFullname(fullname.getFirst() + " " + fullname.getLast());
		employeeDocument.setSalary(employee.getSalary().getValue());
		employeeDocument.setIban(employee.getIban().getValue());
		employeeDocument.setPhoto(new String(employee.getPhoto().getValue()));
		employeeDocument.setBirthYear(employee.getBirthYear().getValue());
		employeeDocument.setDepartment(employee.getDepartment());
		employeeDocument.setFulltime(employee.isFulltime());
		return employeeDocument;
	});
	private static final Converter<EmployeeRequest, Employee> employeeRequestToEmployeeConverter = (context -> {
		var employeeRequest = context.getSource();
		String[] tokens = employeeRequest.getFullname().split("\\s+");
		return new Employee.Builder(employeeRequest.getIdentity()).fullname(tokens[0], tokens[1])
				.iban(employeeRequest.getIban()).salary(employeeRequest.getSalary(), MoneyCurrency.TL)
				.birthYear(employeeRequest.getBirthYear()).fulltime(employeeRequest.isFulltime())
				.department(employeeRequest.getDepartment()).photo(employeeRequest.getPhoto()).build();
	});
	private static final Converter<EmployeeEntity, Employee> employeeEntityToEmployeeConverter = (context -> {
		var employeeEntity = context.getSource();
		String[] tokens = employeeEntity.getFullname().split("\\s+");
		return new Employee.Builder(employeeEntity.getIdentity()).fullname(tokens[0], tokens[1])
				.iban(employeeEntity.getIban()).salary(employeeEntity.getSalary(), MoneyCurrency.TL)
				.birthYear(employeeEntity.getBirthYear()).fulltime(employeeEntity.isFulltime())
				.department(employeeEntity.getDepartment()).photo(employeeEntity.getPhoto()).build();
	});

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.addConverter(employeeRequestToEmployeeConverter, EmployeeRequest.class, Employee.class);
		mapper.addConverter(employeeEntityToEmployeeConverter, EmployeeEntity.class, Employee.class);
		mapper.addConverter(employeeToEmployeeDocumentConverter, Employee.class, EmployeeDocument.class);
		mapper.addConverter(employeeDocumentToEmployeeConverter, EmployeeDocument.class, Employee.class);
		return mapper;
	}
}
