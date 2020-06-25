package com.example.hr.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Department;

public interface EmployeeMongoRepository extends MongoRepository<EmployeeDocument, String>{
	List<EmployeeDocument> findAllByBirthYearBetweenAndDepartment(int fromYear,int toYear,Department department);		
	@Query(value = "{'birthYear': {'$gt': 'fromYear', '$lt': 'toYear'}}")
	List<EmployeeDocument> araBul(int fromYear,int toYear);		

}
