package com.vietlnn.springbootlab.rest.crud.mapper;

import com.vietlnn.springbootlab.rest.crud.dto.EmployeeDto;
import com.vietlnn.springbootlab.rest.crud.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDto> {
  // nothing needed if fields match!
  // Add mapping here only for field name differences
}
