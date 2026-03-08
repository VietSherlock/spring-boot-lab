package com.vietlnn.springbootlab.spring.core.common;

// not registered as a bean by annotation here (@Component) -> manually register in separated class
public class SwimCoach implements Coach {

  public SwimCoach() {
    System.out.println("In constructor: " + getClass().getSimpleName());
  }

  @Override
  public String getDailyWorkout() {
    return "Swim 1000 meters as a warm up";
  }
}
