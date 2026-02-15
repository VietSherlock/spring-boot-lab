package com.vietlnn.springbootlab.dao;

import com.vietlnn.springbootlab.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//@Repository specialized for repositories -> support component scanning & translate JDBC exceptions
@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private final EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional //automatically begin and end a transaction
    public void save(Student student) {
        entityManager.persist(student);
    }
}