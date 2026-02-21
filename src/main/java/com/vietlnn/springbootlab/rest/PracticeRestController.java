package com.vietlnn.springbootlab.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test") // define base url
public class PracticeRestController {

    @GetMapping("/student/serialization")
    public List<StudentPOJO> getTestStudentSerialization()
    {
        List<StudentPOJO> studentPOJOs = new ArrayList<>();
        StudentPOJO studentPOJO1 = new StudentPOJO("Ronaldo", "Cristiano");
        StudentPOJO studentPOJO2 = new StudentPOJO("Viet", "Le Nguyen Ngoc");
        studentPOJOs.add(studentPOJO1);
        studentPOJOs.add(studentPOJO2);
        System.out.println("Student POJO list: " + studentPOJOs);

        return studentPOJOs; //Spring uses Jackson to automatically serialize Java POJOs to JSON and deserialize JSON back to POJOs
    }

    public static class StudentPOJO implements Serializable {
        private String firstName;
        private String lastName;

        public StudentPOJO(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "StudentPOJO{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }
}