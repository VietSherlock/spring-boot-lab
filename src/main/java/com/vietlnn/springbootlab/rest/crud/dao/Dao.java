package com.vietlnn.springbootlab.rest.crud.dao;

import java.util.List;

public interface Dao<T> {

  // find all records
  List<T> findAll();

  // find by entity's id
  T findById(Integer id);
  //
  //  // create an entity
  //  void save(T t);
  //
  //  // update an existing entity
  //  void update(T t);
  //
  //  // delete an entity
  //  void delete(Integer id);
}
