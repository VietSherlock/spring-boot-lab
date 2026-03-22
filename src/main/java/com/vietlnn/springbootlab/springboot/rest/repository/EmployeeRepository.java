package com.vietlnn.springbootlab.springboot.rest.repository;

import com.vietlnn.springbootlab.springboot.rest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// EntityManager used under the hood in JpaRepository's implementation - SimpleJpaRepository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
  // no need to define base CRUD methods
}
