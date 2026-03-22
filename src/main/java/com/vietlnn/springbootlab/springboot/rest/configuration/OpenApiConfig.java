package com.vietlnn.springbootlab.springboot.rest.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(title = "Spring Boot Lab APIs", version = "1.0"),
    tags = { // order tag -> default random
      @Tag(name = "Employee", description = "employee operations"),
      @Tag(name = "Student", description = "student operations"),
      @Tag(name = "Demo")
    })
@Configuration
public class OpenApiConfig {}
