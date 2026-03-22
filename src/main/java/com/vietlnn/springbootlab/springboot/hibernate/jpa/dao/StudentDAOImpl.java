package com.vietlnn.springbootlab.springboot.hibernate.jpa.dao;

import com.vietlnn.springbootlab.springboot.hibernate.jpa.entity.Student;
import com.vietlnn.springbootlab.springboot.hibernate.jpa.entity.StudentSearchCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
 @Repository specialized for repositories
 -> support component scanning & translate JDBC exceptions
*/
@Repository
public class StudentDAOImpl implements StudentDAO {

  // define field for entity manager
  private final EntityManager entityManager;

  private String FIRST_NAME = "firstName";
  private String LAST_NAME = "lastName";
  private String EMAIL = "email";

  // inject entity manager using constructor injection
  @Autowired
  public StudentDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  // implement save method
  @Override
  @Transactional // automatically begin and end a transaction -> no need for select statement/query
  public void save(Student student) {
    entityManager.persist(student);
  }

  @Override
  public Student findById(Integer id) {
    return entityManager.find(Student.class, id);
  }

  @Override
  public List<Student> findAll() {
    // Student is name of JPA Entity -> not table name
    TypedQuery<Student> query =
        entityManager.createQuery("from Student order by lastName", Student.class);
    return query.getResultList();
  }

  @Override
  public List<Student> findByLastName(String lastName) {
    // create query
    TypedQuery<Student> query =
        entityManager.createQuery(
            "from Student where lastName = :lastNameValue",
            Student.class); // :lastNameValue as placeholder

    // set query parameters
    query.setParameter("lastNameValue", lastName);

    // return query results
    return query.getResultList();
  }

  @Override
  public List<Student> identifyStudents(StudentSearchCriteria searchCriteria) {
    if (searchCriteria == null) {
      System.out.println("SearchCriteria is empty!");
      return null;
    }

    // build criteria
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
    Root<Student> studentRoot = criteriaQuery.from(Student.class);

    // build predicates based on searchCriteria
    List<Predicate> predicates = buildPredicates(criteriaBuilder, studentRoot, searchCriteria);

    criteriaQuery.select(studentRoot).where(predicates.toArray(new Predicate[0]));
    return entityManager.createQuery(criteriaQuery).getResultList();
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

  @Override
  @Transactional
  public void deleteBySearchCriteria(StudentSearchCriteria searchCriteria) {
    // build criteria
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaDelete<Student> criteriaDelete = criteriaBuilder.createCriteriaDelete(Student.class);
    Root<Student> studentRoot = criteriaDelete.from(Student.class);

    // create predicate
    List<Predicate> predicates = buildPredicates(criteriaBuilder, studentRoot, searchCriteria);

    criteriaDelete.where(predicates.toArray(new Predicate[0]));
    entityManager.createQuery(criteriaDelete).executeUpdate();
    entityManager.clear(); // clear caching after bulk update/delete (executeUpdate())
  }

  @Override
  @Transactional
  public int deleteAll() {
    return entityManager.createQuery("delete from Student").executeUpdate();
  }

  private List<Predicate> buildPredicates(
      CriteriaBuilder criteriaBuilder,
      Root<Student> studentRoot,
      StudentSearchCriteria searchCriteria) {
    List<Predicate> predicates = new ArrayList<>();

    if (StringUtils.isNotBlank(searchCriteria.getFirstName())) {
      predicates.add(
          criteriaBuilder.equal(studentRoot.get(FIRST_NAME), searchCriteria.getFirstName()));
    }
    if (StringUtils.isNotBlank(searchCriteria.getLastName())) {
      predicates.add(
          criteriaBuilder.equal(studentRoot.get(LAST_NAME), searchCriteria.getLastName()));
    }
    if (StringUtils.isNotBlank(searchCriteria.getEmail())) {
      predicates.add(criteriaBuilder.equal(studentRoot.get(EMAIL), searchCriteria.getEmail()));
    }
    return predicates;
  }
}
