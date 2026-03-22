package com.vietlnn.springbootlab.springboot.core.config;

import com.vietlnn.springbootlab.springboot.core.common.Coach;
import com.vietlnn.springbootlab.springboot.core.common.SwimCoach;
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
