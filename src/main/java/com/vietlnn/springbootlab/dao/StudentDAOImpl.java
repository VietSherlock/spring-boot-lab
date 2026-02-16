package com.vietlnn.springbootlab.dao;

import com.vietlnn.springbootlab.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional //automatically begin and end a transaction -> no need for select statement/query
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        //Student is name of JPA Entity -> not table name
        TypedQuery<Student> query = entityManager.createQuery("from Student order by lastName", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        // create query
        TypedQuery<Student> query = entityManager.createQuery("from Student where lastName = :lastNameValue", Student.class); //:lastNameValue as placeholder

        // set query parameters
        query.setParameter("lastNameValue", lastName);

        // return query results
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // select student by id
        Student foundStudent = entityManager.find(Student.class, id);

        // display found student
        System.out.println("Found Student: " + foundStudent);

        // delete found student
        entityManager.remove(foundStudent);
    }
}