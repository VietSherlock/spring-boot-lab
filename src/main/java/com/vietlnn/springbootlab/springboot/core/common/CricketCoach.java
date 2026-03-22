package com.vietlnn.springbootlab.springboot.core.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Lazy
@Primary
@Component
public class CricketCoach implements Coach {

  @Value("${coach.name}")
  private String coachName;

  @Value("${team.name}")
  private String teamName;

  public CricketCoach() {
    System.out.println("Constructor: " + getClass().getSimpleName());
  }

  @Override
  public String getDailyWorkout() {
    return String.format(
        "HockeyCoach - %s: %s needs to practice 15 minutes every day!", coachName, teamName);
  }
}
