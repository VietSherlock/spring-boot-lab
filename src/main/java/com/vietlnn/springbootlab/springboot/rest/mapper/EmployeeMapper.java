package com.vietlnn.springbootlab.springboot.rest.mapper;

import com.vietlnn.springbootlab.springboot.rest.dto.EmployeeDto;
import com.vietlnn.springbootlab.springboot.rest.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDto> {
  // nothing needed if fields match!
  // Add mapping here only for field name differences
}
