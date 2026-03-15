package com.vietlnn.springbootlab.rest.crud.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

// generic interface with all common CRUD operations

/**
 * Base DAO interface includes base CRUD operations for all entities
 *
 * <p>BaseDao <-> AbstractJpaDao is the same idea with JpaRepository <-> SimpleJpaRepository
 *
 * @param <T> Entity type
 * @param <I> ID type
 */
public interface BaseDao<T, I extends Serializable> {

  // find by entity's id
  Optional<T> findById(I id);

  // find all entities
  List<T> findAll();

  // create an entity
  T save(T entity);

  // update an existing entity
  T update(T entity);

  // delete an entity
  void delete(T entity);

  // delete entity by its ID
  void deleteById(I id);
}
