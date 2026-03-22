package com.vietlnn.springbootlab.springboot.core.serialization;

import java.io.Serializable;

public class DemoClassWithoutSerializable implements Serializable {

  private String name;

  public DemoClassWithoutSerializable(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
