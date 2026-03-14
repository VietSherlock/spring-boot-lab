package com.vietlnn.springbootlab.rest.crud.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Abstract class includes base CRUD implementations for all entities.
 *
 * <p>Used EntityManager to communicate with database.
 *
 * @param <T> entity type
 * @param <I> ID type
 */
public abstract class AbstractJpaDao<T, I extends Serializable> implements BaseDao<T, I> {

  @PersistenceContext protected EntityManager entityManager;

  private final Class<T> entityClass;

  @Autowired
  protected AbstractJpaDao(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  @Override
  public Optional<T> findById(I id) {
    return Optional.ofNullable(entityManager.find(entityClass, id));
  }

  @Override
  public List<T> findAll() {
    return entityManager
        .createQuery(String.format("from %s", entityClass.getSimpleName()), entityClass)
        .getResultList();
  }

  @Override
  public T save(T entity) {
    entityManager.persist(entity);
    return entity;
  }

  @Override
  public T update(T entity) {
    return entityManager.merge(entity);
  }

  /*-
   * delete(entity) called
   *         │
   *         ▼
   * entityManager.contains(entity)? -> check if entity is managed?
   *         │
   *    YES  │  NO
   *         │   │
   *         │   ▼
   *         │  merge(entity) ──► returns a NEW managed copy (re-attach the Detached entity)
   *         │         │
   *         ▼         ▼
   *     entityManager.remove( managed entity )
   *                 │
   *                 ▼
   *         DELETE scheduled on next flush
   */
  @Override
  public void delete(T entity) {
    entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
  }

  @Override
  public void deleteById(I id) {
    findById(id).ifPresent(this::delete);
  }
}
