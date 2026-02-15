package com.vietlnn.springbootlab.dao;

import com.vietlnn.springbootlab.entity.Student;

//DAO is a design pattern that provides an abstract interface for accessing data from a database (or any persistence mechanism)
//Encapsulate CRUD operation for a specific entity
//Separate business logic from persistence details
public interface StudentDAO {
    public void save (Student student);
}