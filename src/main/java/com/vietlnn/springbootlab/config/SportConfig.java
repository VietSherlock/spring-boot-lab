package com.vietlnn.springbootlab.config;

import com.vietlnn.springbootlab.common.Coach;
import com.vietlnn.springbootlab.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

  @Bean("swimCoachId") // set custom bean id
  public Coach swimCoach() // default bean id -> method name
      {
    return new SwimCoach();
  }
}
