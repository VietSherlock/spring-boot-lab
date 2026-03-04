package com.vietlnn.springbootlab.rest.crud.controller;

import com.vietlnn.springbootlab.rest.crud.dto.Student;
import com.vietlnn.springbootlab.rest.crud.dto.StudentErrorResponse;
import com.vietlnn.springbootlab.rest.crud.exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // define base uri
public class StudentRestController {

  private List<Student> students;

  // return a list of students
  @GetMapping("/students")
  public List<Student> getStudents() {
    // Spring uses Jackson to automatically serialize Java POJOs to JSON
    // and deserialize JSON back to POJOs
    return students;
  }

  // return student with id match path variable value
  // by default path variable name should the same as argument
  @GetMapping("/students/{studentId}")
  public Student getStudent(@PathVariable int studentId) {
    if (studentId >= students.size() || studentId < 0) {
      throw new StudentNotFoundException("Student id not found - " + studentId);
    }
    return students.get(studentId);
  }

  @ExceptionHandler
  public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exception) {
    StudentErrorResponse error = new StudentErrorResponse();
    error.setStatus(HttpStatus.NOT_FOUND.value());
    error.setMessage(exception.getMessage());
    error.setTimeStamp(System.currentTimeMillis());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  // use init method to initialize student data -> store in memory
  @PostConstruct
  public void loadStudent() {
    students = new ArrayList<>();
    students.add(new Student("Cristiano", "Ronaldo"));
    students.add(new Student("Viet", "Le Nguyen Ngoc"));
  }
}
