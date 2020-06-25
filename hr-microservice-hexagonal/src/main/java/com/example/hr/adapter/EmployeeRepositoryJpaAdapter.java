package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.domain.Employee;
import com.example.hr.domain.FullName;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.meta.Persistence;
import com.example.hr.meta.PersistenceTarget;
import com.example.hr.orm.EmployeeEntity;
import com.example.hr.repository.EmployeeJpaRepository;
import com.example.hr.repository.EmployeeRepository;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Repository
@Persistence(PersistenceTarget.JPA_MYSQL)
public class EmployeeRepositoryJpaAdapter implements EmployeeRepository {
	@Autowired
	private EmployeeJpaRepository empJpaRepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public Optional<Employee> findByIdentity(TcKimlikNo identity) {
		var emp = empJpaRepo.findById(identity.getValue());
		if (emp.isPresent()) {
			return Optional.of(mapper.map(emp.get(), Employee.class));
		}
		return Optional.empty();
	}

	@Override
	@Transactional
	public void save(Employee employee) {
		EmployeeEntity entity = new EmployeeEntity();
		entity.setIdentity(employee.getIdentityNo().getValue());
		FullName fullname = employee.getFullname();
		entity.setFullname(fullname.getFirst() + " " + fullname.getLast());
		entity.setSalary(employee.getSalary().getValue());
		entity.setIban(employee.getIban().getValue());
		entity.setBirthYear(employee.getBirthYear().getValue());
		entity.setDepartment(employee.getDepartment());
		entity.setPhoto(employee.getPhoto().getValue());
		entity.setFulltime(employee.isFulltime());
		empJpaRepo.save(entity);
	}

	@Override
	@Transactional
	public void remove(Employee employee) {
		var identity = employee.getIdentityNo().getValue();
		empJpaRepo.deleteById(identity);
	}

}
