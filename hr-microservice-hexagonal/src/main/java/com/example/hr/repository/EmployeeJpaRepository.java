package com.example.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hr.orm.EmployeeEntity;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, String> {

}
