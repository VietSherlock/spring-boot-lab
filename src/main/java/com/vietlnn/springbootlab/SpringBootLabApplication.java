package com.vietlnn.springbootlab;

import com.vietlnn.springbootlab.dao.StudentDAO;
import com.vietlnn.springbootlab.entity.Student;
import com.vietlnn.springbootlab.entity.StudentSearchCriteria;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootLabApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootLabApplication.class, args);
  }

  @Bean
  //CommandLineRunner is executed after the Spring Beans have been loaded
  public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
    return runner -> {
//			createStudent(studentDAO);
//			createStudents(studentDAO);
//			readStudent(studentDAO);
//			queryForStudents(studentDAO);
//			readStudentByLastName(studentDAO);
//			updateStudent(studentDAO);
//			deleteStudentById(studentDAO);
//			identifyStudents(studentDAO);
//			deleteStudentBySearchCriteria(studentDAO);
//			deleteAllStudents(studentDAO);
    };
  }

  private void deleteAllStudents(StudentDAO studentDAO) {
    System.out.println("Deleting all students...");
    int numRowsDeleted = studentDAO.deleteAll();
    System.out.println("Deleted student number: " + numRowsDeleted);
  }

  private void deleteStudentBySearchCriteria(StudentDAO studentDAO) {
    StudentSearchCriteria searchCriteria = new StudentSearchCriteria();
    searchCriteria.setFirstName("Marcus1");

    System.out.println("Deleting Student with SearchCriteria: " + searchCriteria);
    studentDAO.deleteBySearchCriteria(searchCriteria);
  }

  private void identifyStudents(StudentDAO studentDAO) {
    StudentSearchCriteria searchCriteria = new StudentSearchCriteria();
    searchCriteria.setLastName("Aurelius");
    List<Student> foundStudents = studentDAO.identifyStudents(searchCriteria);
    System.out.println("Found Students size: " + foundStudents.size());
    System.out.println("Found Students: " + foundStudents);
  }

  private void deleteStudentById(StudentDAO studentDAO) {
    int studentIdToBeDeleted = 1;
    System.out.printf("Deleting student with id '%d'...%n", studentIdToBeDeleted);
    studentDAO.delete(studentIdToBeDeleted);
  }

  private void updateStudent(StudentDAO studentDAO) {
    // retrieve student by ID
    int studentId = 1;
    System.out.println("Getting student with id: " + studentId);
    Student foundStudent = studentDAO.findById(studentId);
    System.out.println("Updating student...");

    // change first name to "Scooby"
    foundStudent.setFirstName("Scooby");
    studentDAO.update(foundStudent);

    // display updated student
    System.out.println("Updated student: " + foundStudent);
  }

  private void readStudentByLastName(StudentDAO studentDAO) {
    // retrieve students by lastName
    List<Student> students = studentDAO.findByLastName("Aurelius");

    // display list of students
    students.forEach(System.out::println);
  }

  private void queryForStudents(StudentDAO studentDAO) {
    // get list of students
    List<Student> students = studentDAO.findAll();

    // display list of students
    students.forEach(System.out::println);
  }

  private void readStudent(StudentDAO studentDAO) {
    // create student object
    Student student = new Student("Viet", "Le Nguyen Ngoc", "vietlnn@gmail.com");

    // save student object
    createStudent(studentDAO, student);

    // display student saved by its primary key
    Student foundStudent = studentDAO.findById(student.getId());
    System.out.println("Found student: " + foundStudent);
  }

  private void createStudents(StudentDAO studentDAO) {
    // create students
    Student student1 = new Student("Marcus1", "Aurelius", "MarcusA@gmail.com", "test");
    Student student2 = new Student("Marcus2", "Aurelius", "MarcusA@gmail.com", "test");
    Student student3 = new Student("Marcus3", "Aurelius", "MarcusA@gmail.com", "test");
    List<Student> students = new ArrayList<>(Arrays.asList(student1, student2, student3));

    // create students
    for (Student student : students) {
      createStudent(studentDAO, student);
    }
  }

  private void createStudent(StudentDAO studentDAO, Student student) {

    // create the student object
    System.out.println("Creating new student object...");

    // save the student object
    System.out.println("Saving the student...");
    studentDAO.save(student);

    // display the saved student id
    System.out.println("Saved student. Generated id: " + student.getId());
  }
}