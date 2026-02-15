package com.vietlnn.springbootlab;

import com.vietlnn.springbootlab.dao.StudentDAO;
import com.vietlnn.springbootlab.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringBootLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLabApplication.class, args);
	}

	@Bean
	//CommandLineRunner is executed after the Spring Beans have been loaded
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
//			createStudent(studentDAO);
			createStudents(studentDAO);
		};
	}

	private void createStudents(StudentDAO studentDAO) {
		// create students
        Student student1 = new Student("Marcus1", "Aurelius", "MarcusA@gmail.com", "test");
		Student student2 = new Student("Marcus2", "Aurelius", "MarcusA@gmail.com", "test");
		Student student3 = new Student("Marcus3", "Aurelius", "MarcusA@gmail.com", "test");
        List<Student> students = new ArrayList<>(Arrays.asList(student1, student2, student3));

		// create students
		for(Student student : students){
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