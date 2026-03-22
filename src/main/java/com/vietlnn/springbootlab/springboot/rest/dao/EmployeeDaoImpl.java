package com.vietlnn.springbootlab.springboot.rest.dao;

import com.vietlnn.springbootlab.springboot.rest.entity.Employee;
import org.springframework.stereotype.Repository;

/** JPA + Hibernate */
@Repository
public class EmployeeDaoImpl extends AbstractJpaDao<Employee, Integer> implements EmployeeDao {

  public EmployeeDaoImpl() {
    super(Employee.class);
  }
}
