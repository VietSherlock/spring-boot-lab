package com.vietlnn.springbootlab.dao;

import com.vietlnn.springbootlab.entity.Student;
import com.vietlnn.springbootlab.entity.StudentSearchCriteria;

import java.util.List;

//DAO is a design pattern that provides an abstract interface for accessing data from a database (or any persistence mechanism)
//Encapsulate CRUD operation for a specific entity
//Separate business logic from persistence details
public interface StudentDAO {

    public void save (Student student);
    public Student findById(Integer id);
    public List<Student> findAll();
    public List<Student> findByLastName(String lastName);
    public List<Student> identifyStudents(StudentSearchCriteria searchCriteria);
    public void update(Student student);
    public void delete(Integer id);
    public void deleteBySearchCriteria(StudentSearchCriteria searchCriteria);
    public int deleteAll();
}