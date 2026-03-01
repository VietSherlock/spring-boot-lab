package com.vietlnn.springbootlab.rest.crud.controller;

import com.vietlnn.springbootlab.rest.crud.dto.Student;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // define base uri
public class StudentRestController {

  // return a list of students
  @GetMapping("/students")
  public List<Student> getStudents() {
    //Spring uses Jackson to automatically serialize Java POJOs to JSON and deserialize JSON back to POJOs
    return Arrays.asList(new Student("Cristiano", "Ronaldo"),
        new Student("Viet", "Le Nguyen Ngoc"));
  }
}