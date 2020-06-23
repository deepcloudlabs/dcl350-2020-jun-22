package com.example.hr.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryAdapter implements EmployeeRepository {
    //TODO: Spring Data Jpa
	@Override
	public Optional<Employee> findByIdentity(TcKimlikNo identity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Employee employee) {
		// TODO Auto-generated method stub
		
	}

}
