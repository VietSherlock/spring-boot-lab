package com.vietlnn.springbootlab.rest.crud.dao;

import com.vietlnn.springbootlab.rest.crud.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeBaseDaoImpl extends AbstractJpaBaseDao<Employee, Integer>
    implements EmployeeBaseDao {

  public EmployeeBaseDaoImpl() {
    super(Employee.class);
  }
}
