package com.vietlnn.springbootlab.rest.crud.mapper;

import java.util.List;

/**
 * Define base mapping methods for entities and DTOs fields match
 *
 * @param <E> Entity type
 * @param <D> DTO type
 */
// NOTE:
// Avoid one giant mapper class handling all entities - it defeats MapStructs compile-time safety
public interface BaseMapper<E, D> {

  // map DTO to entity
  E toEntity(D dto);

  // map entity to DTO
  D toDto(E entity);

  // map DTOs to entities
  List<E> toEntities(List<D> dtos);

  // map entities to DTOs
  List<D> toDtos(List<E> entities);
}
