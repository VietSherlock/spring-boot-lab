package com.vietlnn.springbootlab.rest.crud.controller;

import com.vietlnn.springbootlab.rest.crud.dto.EmployeeDto;
import com.vietlnn.springbootlab.rest.crud.entity.Employee;
import com.vietlnn.springbootlab.rest.crud.exception.BadRequestException;
import com.vietlnn.springbootlab.rest.crud.exception.NotFoundException;
import com.vietlnn.springbootlab.rest.crud.mapper.EmployeeMapper;
import com.vietlnn.springbootlab.rest.crud.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

/*
 * Overall flow:
 * Rest Controller <-> Service <-> DAO <-> Database
 * */
@RestController
@RequestMapping("/api")
@Tag(name = "Employee") // custom Swagger-UI tag
public class EmployeeRestController {

  private final EmployeeService employeeService;
  private final EmployeeMapper employeeMapper;
  private final JsonMapper jsonMapper;

  @Autowired
  public EmployeeRestController(
      //      @Qualifier("employeeServiceImpl") EmployeeService employeeService, // specify bean
      EmployeeService employeeService, EmployeeMapper employeeMapper, JsonMapper jsonMapper) {
    this.employeeService = employeeService;
    this.employeeMapper = employeeMapper;
    this.jsonMapper = jsonMapper;
  }

  /**
   * GET - /api/employees
   *
   * @return All employees if found - 200 OK. Otherwise, 404 - NOT FOUND.
   */
  @GetMapping("/employees")
  @Operation(summary = "Get all employees")
  @ApiResponse(responseCode = "200")
  @ApiResponse(responseCode = "404")
  public ResponseEntity<List<Employee>> getEmployees() {

    List<Employee> employees = employeeService.findAll();

    // return 404 if not found
    if (CollectionUtils.isEmpty(employees)) {
      throw new NotFoundException("No employee found!");
    }
    return ResponseEntity.ok(employees);
  }

  /**
   * GET - /api/employees/{employeeId}
   *
   * @param employeeId ID of an employee.
   * @return An employee matching with input employeeId. Otherwise, return 404 - NOT FOUND.
   */
  @Operation(
      summary = "Get employee by ID",
      description = "Return a single employee based on their ID")
  @GetMapping("/employees/{employeeId}")
  public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer employeeId) {

    Optional<Employee> foundEmployee = employeeService.findById(employeeId);

    // return 404 if not found
    if (foundEmployee.isEmpty()) {
      throw new NotFoundException(String.format("Employee not found with id '%s'", employeeId));
    }
    return ResponseEntity.ok(employeeMapper.toDto(foundEmployee.get()));
  }

  /**
   * POST - /api/employees
   *
   * @param employeeDto Request data to create an employee.
   * @return The employee created.
   */
  @PostMapping("/employees")
  public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDto employeeDto) {
    Employee createdEmployee = employeeService.save(employeeMapper.toEntity(employeeDto));
    return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
  }

  /**
   * PUT - /api/employees
   *
   * @param employee Employee data to be updated.
   * @return The employee updated.
   */
  @PutMapping("/employees")
  public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
    Employee updatedemployee = employeeService.save(employee);
    return ResponseEntity.ok(updatedemployee);
  }

  /**
   * PATH - /api/employees/{employeeId}
   *
   * @param employeeId Employee's ID.
   * @param patchPayload Partial employee data to be updated.
   * @return Employee patched.
   */
  @PatchMapping("/employees/{employeeId}")
  public ResponseEntity<Employee> patchEmployee(
      @PathVariable Integer employeeId, @RequestBody Map<String, Object> patchPayload) {

    Employee foundEmployee = employeeService.lookupById(employeeId);

    if (patchPayload.containsKey("id")) {
      throw new BadRequestException("Employee id not allowed in request body - " + employeeId);
    }

    // apply the partial updates to the existing employee
    Employee employeeToBePatched = jsonMapper.updateValue(foundEmployee, patchPayload);

    Employee patchedEmployee = employeeService.save(employeeToBePatched);
    return ResponseEntity.ok(patchedEmployee);
  }

  /**
   * DELETE - /api/employees/{employeeId}
   *
   * @param employeeId ID of employee to be deleted.
   * @return A String indicate that employee with provided ID deleted.
   */
  @DeleteMapping("/employees/{employeeId}")
  public String deleteEmployeeById(@PathVariable Integer employeeId) {

    Optional<Employee> foundEmployee = employeeService.findById(employeeId);

    if (foundEmployee.isEmpty()) {
      throw new NotFoundException(
          String.format("Employee with id '%s' cannot be found.", employeeId));
    }
    employeeService.deleteById(employeeId);
    return String.format("Deleted employee with id '%s'", employeeId);
  }
}
