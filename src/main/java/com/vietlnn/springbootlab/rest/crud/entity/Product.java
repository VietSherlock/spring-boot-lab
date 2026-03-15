package com.vietlnn.springbootlab.rest.crud.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // no-arg constructor required by JPA
@Entity
@Table(name = "product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "product_no")
  private String productNo;

  @Column(name = "product_name")
  private String productName;

  @Column(name = "price")
  private Integer price;

  public Product(String productNo, String productName, Integer price) {
    this.productNo = productNo;
    this.productName = productName;
    this.price = price;
  }
}
