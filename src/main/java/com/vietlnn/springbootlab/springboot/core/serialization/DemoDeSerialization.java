package com.vietlnn.springbootlab.springboot.core.serialization;

import java.io.Serial;
import java.io.Serializable;

public class DemoDeSerialization implements Serializable {

  @Serial private static final long serialVersionUID = 1L;
  private static int b;
  private final int c;
  private final transient int d;
  private String demoName;
  private transient int a;
  // instance member of serialized class must implement Serializable either to avoid error during
  // serialization process
  private DemoClassWithoutSerializable classWithoutSerializable =
      new DemoClassWithoutSerializable("FirstName");

  public DemoDeSerialization(String demoName, int a, int b, int c, int d) {
    this.demoName = demoName;
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
  }

  public static int getB() {
    return b;
  }

  public String getDemoName() {
    return demoName;
  }

  public void setDemoName(String demoName) {
    this.demoName = demoName;
  }

  public DemoClassWithoutSerializable getClassWithoutSerializable() {
    return classWithoutSerializable;
  }

  public void setClassWithoutSerializable(DemoClassWithoutSerializable classWithoutSerializable) {
    this.classWithoutSerializable = classWithoutSerializable;
  }

  public int getA() {
    return a;
  }

  public int getC() {
    return c;
  }

  public int getD() {
    return d;
  }

  @Override
  public String toString() {
    return "DemoDeSerialization{"
        + "demoName='"
        + demoName
        + '\''
        + ", a="
        + a
        + ", c="
        + c
        + ", d="
        + d
        + ", classWithoutSerializable="
        + classWithoutSerializable
        + '}';
  }
}
