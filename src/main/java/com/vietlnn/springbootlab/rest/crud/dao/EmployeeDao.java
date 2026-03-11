package com.vietlnn.springbootlab.rest.crud.dao;

import com.vietlnn.springbootlab.rest.crud.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao implements Dao<Employee> {

  private final EntityManager entityManager;

  @Autowired
  public EmployeeDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<Employee> findAll() {
    // create a query
    TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);

    // execute query and get result list
    return query.getResultList();
  }

  @Override
  public Employee findById(Integer id) {
    return entityManager.find(Employee.class, id);
  }
}
