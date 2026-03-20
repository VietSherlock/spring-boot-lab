package com.vietlnn.springbootlab.rest.crud.controller;

import com.vietlnn.springbootlab.rest.crud.dto.StudentDto;
import com.vietlnn.springbootlab.rest.crud.exception.NotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // define base uri
@Tag(name = "Student")
public class StudentRestController {

  private List<StudentDto> studentDtos;

  // return a list of students
  @GetMapping("/students")
  public List<StudentDto> getStudents() {
    // Spring uses Jackson to automatically serialize Java POJOs to JSON
    // and deserialize JSON back to POJOs
    return studentDtos;
  }

  // return student with id match path variable value
  // by default path variable name should the same as argument
  @GetMapping("/students/{studentId}")
  public StudentDto getStudent(@PathVariable int studentId) {
    if (studentId >= studentDtos.size() || studentId < 0) {
      throw new NotFoundException("Student id not found - " + studentId);
    }
    return studentDtos.get(studentId);
  }

  // use init method to initialize student data -> store in memory
  @PostConstruct
  public void loadStudent() {
    studentDtos = new ArrayList<>();
    studentDtos.add(new StudentDto("Cristiano", "Ronaldo"));
    studentDtos.add(new StudentDto("Viet", "Le Nguyen Ngoc"));
  }
}
