package com.example.hr.controller;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.application.EmployeeApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.EmployeeRequest;
import com.example.hr.dto.EmployeeResponse;
import com.example.hr.orm.EmployeeEntity;
import com.example.validation.TcKimlik;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@RestController
@RequestScope
@RequestMapping("/employees")
@CrossOrigin
@Validated
public class EmployeeController {
	@Autowired
	private EmployeeApplication employeeApplication;
	@Autowired
	private ModelMapper mapper;

	@PostMapping
	public EmployeeResponse hireEmployee(@RequestBody @Validated EmployeeRequest request) {
		Employee emp = mapper.map(request, Employee.class);
		employeeApplication.hireEmployee(emp);
		return new EmployeeResponse("success");
	}

	@PutMapping("{identity}")
	public void updateEmployee(@PathVariable @TcKimlik String identity, @Validated @RequestBody EmployeeEntity e) {

	}

	@PatchMapping("{identity}")
	public void patchEmployee(@PathVariable @TcKimlik String identity, Map<String, Object> employee) {
		EmployeeEntity employeeEntity = null;
		var clazz = EmployeeEntity.class;
		employee.forEach((field, value) -> {
			try {
				clazz.getDeclaredField(field).set(employeeEntity, value);
			} catch (Exception e) {
			}
		});
	}

	@DeleteMapping("{identity}")
	public EmployeeResponse fireEmployee(@PathVariable @TcKimlik String identity) {
		var removedEmp = employeeApplication.fireEmployee(TcKimlikNo.of(identity));
		if (removedEmp.isPresent())
			return new EmployeeResponse("success");
		return new EmployeeResponse("fail");
	}
}
