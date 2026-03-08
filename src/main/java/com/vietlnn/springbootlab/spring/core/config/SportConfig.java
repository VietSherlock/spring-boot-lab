package com.vietlnn.springbootlab.spring.core.config;

import com.vietlnn.springbootlab.spring.core.common.Coach;
import com.vietlnn.springbootlab.spring.core.common.SwimCoach;
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
