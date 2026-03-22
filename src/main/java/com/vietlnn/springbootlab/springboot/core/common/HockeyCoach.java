package com.vietlnn.springbootlab.springboot.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy // Bean is only initialized if needed for DI
@Component
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // new instance for each injection with scope
// prototype
public class HockeyCoach implements Coach {

  @Value("${coach.name}")
  private String coachName;

  @Value("${team.name}")
  private String teamName;

  public HockeyCoach() {
    System.out.println("Constructor: " + getClass().getSimpleName());
  }

  // define custom init method -> run after Bean instantiated
  @PostConstruct
  public void setUpStartup() {
    System.out.println("In setUpStartup(): " + getClass().getSimpleName());
  }

  // define custom destroy method -> run after container shutdown
  @PreDestroy
  public void cleanUpBeforeDestroy() {
    System.out.println("In cleanUpBeforeDestroy(): " + getClass().getSimpleName());
  }

  @Override
  public String getDailyWorkout() {
    return String.format(
        "HockeyCoach - %s: %s needs to practice 15 minutes every day!", coachName, teamName);
  }
}
