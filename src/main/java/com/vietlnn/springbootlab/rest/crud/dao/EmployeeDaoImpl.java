package com.vietlnn.springbootlab.rest.crud.dao;

import com.vietlnn.springbootlab.rest.crud.entity.Employee;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl extends AbstractJpaDao<Employee, Integer> implements EmployeeDao {

  public EmployeeDaoImpl(Class<Employee> entityClass, EntityManager entityManager) {
    super(entityClass, entityManager);
  }
}
