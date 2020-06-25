package com.example.hr.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;
import com.example.hr.domain.FullName;
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
		TypeMap<EmployeeDocument, Employee> employeeDocumentToEmployeeTypeMap = mapper
				.getTypeMap(EmployeeDocument.class, Employee.class);
		if (employeeDocumentToEmployeeTypeMap == null) {
			employeeDocumentToEmployeeTypeMap = mapper.createTypeMap(EmployeeDocument.class, Employee.class);
		}
		employeeRequestToEmployeeTypeMap.setProvider(provisionRequest -> {
			var employeeDocument = EmployeeDocument.class.cast(provisionRequest.getSource());
			String[] tokens = employeeDocument.getFullname().split("\\w+");
			return new Employee.Builder(employeeDocument.getIdentity()).fullname(tokens[0], tokens[1])
					.iban(employeeDocument.getIban()).salary(employeeDocument.getSalary(), MoneyCurrency.TL)
					.birthYear(employeeDocument.getBirthYear()).fulltime(employeeDocument.getFulltime())
					.department(employeeDocument.getDepartment()).photo(employeeDocument.getPhoto().getBytes()).build();
		});
		TypeMap<Employee, EmployeeDocument> employeeToEmployeeDocumentTypeMap = mapper.getTypeMap(Employee.class,
				EmployeeDocument.class);
		if (employeeToEmployeeDocumentTypeMap == null) {
			employeeToEmployeeDocumentTypeMap = mapper.createTypeMap(Employee.class, EmployeeDocument.class);
		}
		employeeToEmployeeDocumentTypeMap.setProvider(provisionRequest -> {
			var employee = Employee.class.cast(provisionRequest.getSource());
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
		return mapper;
	}
}
