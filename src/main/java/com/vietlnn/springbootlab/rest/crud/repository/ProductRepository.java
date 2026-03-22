package com.vietlnn.springbootlab.rest.crud.repository;

import com.vietlnn.springbootlab.rest.crud.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Used Spring Data REST + Spring Data JPA to quickly generate base REST APIs
 *
 * <p>Only need Entity + Repository (extend JpaRepository) + Dependency (defined in POM file)
 */
/*
@RepositoryRestResource(
   path = "products", // URL path -> optional, specify if a different path
   collectionResourceRel = "embeddedProducts", // define HATEOAS's key - _embedded
   itemResourceRel = "linkProducts") // define HATEOAS's key - _links
*/
public interface ProductRepository extends JpaRepository<Product, Integer> {}
