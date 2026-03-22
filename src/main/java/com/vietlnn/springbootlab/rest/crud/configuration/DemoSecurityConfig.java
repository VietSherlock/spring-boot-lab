package com.vietlnn.springbootlab.rest.crud.configuration;

import com.vietlnn.springbootlab.rest.crud.entity.UserRole;
import javax.sql.DataSource;
import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

  public static final String EMPLOYEE_PATH = "/api/employees";

  // TODO: secure Spring Boot REST with JPA/Hibernate later.

  /**
   * JDBC authentication for encrypted and plain-text passwords.
   *
   * @param dataSource which source data is using.
   * @return UserDetailsManager's instance.
   */
  @Bean
  public UserDetailsManager userDetailsManager(DataSource dataSource) {

    // return only JdbcUserDetails instance if the default schema (users, authorities tables) used
    //        return new JdbcUserDetailsManager(dataSource);

    // custom db schema (members <-> users, roles <-> authorities tables)
    JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

    // define query to retrieve a user by username!
    userDetailsManager.setUsersByUsernameQuery(
        "select user_id, pw, active from members where user_id = ?");

    // define query to retrieve authorities/roles by username
    userDetailsManager.setAuthoritiesByUsernameQuery(
        "select user_id, role from roles where user_id = ?");

    return userDetailsManager;
  }

  /**
   * Authorize endpoints by ROLE
   *
   * @param httpSecurity
   * @return SecurityFilterChain's instance
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {

    // set role for endpoints
    httpSecurity.authorizeHttpRequests(
        auth ->
            auth.requestMatchers(HttpMethod.GET, EMPLOYEE_PATH)
                .hasRole(UserRole.EMPLOYEE.name())
                .requestMatchers(HttpMethod.GET, EMPLOYEE_PATH + "/**")
                .hasRole(UserRole.EMPLOYEE.name())
                .requestMatchers(HttpMethod.POST, EMPLOYEE_PATH)
                .hasRole(UserRole.MANAGER.name())
                .requestMatchers(HttpMethod.PUT, EMPLOYEE_PATH)
                .hasRole(UserRole.MANAGER.name())
                .requestMatchers(HttpMethod.PATCH, EMPLOYEE_PATH + "/**")
                .hasRole(UserRole.MANAGER.name())
                .requestMatchers(HttpMethod.DELETE, EMPLOYEE_PATH + "/**")
                .hasRole(UserRole.ADMIN.name())
                // allow H2 console without login
                .requestMatchers(PathRequest.toH2Console())
                .permitAll()
                .anyRequest()
                .authenticated());

    // use HTTP Basic authentication
    httpSecurity.httpBasic(Customizer.withDefaults());

    // disable CSRF (Cross-Site Request Forgery)
    httpSecurity.csrf(AbstractHttpConfigurer::disable);

    // CSRF protection block H2 console submits login
    // httpSecurity.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));

    // allow H2's iframe to render in the browser
    httpSecurity.headers(
        header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

    return httpSecurity.build();
  }

  /**
   * In-memory authentication for plain-text passwords.
   *
   * <p>NOTE: The username and pwd here will override the one define in properties file.
   */
  /*
  @Bean
  public InMemoryUserDetailsManager userDetailsManager() {

    // Spring Security stores password in the format: {id}encodedPassword
    // {id} encrypt type. "noop" -> plain text. "bcrypt" BCrypt pwd hashing
    UserDetails john =
        User.builder().username("john").password("{noop}test123").roles(UserRole.EMPLOYEE.name()).build();

    UserDetails mary =
        User.builder()
            .username("mary")
            .password("{noop}test123")
            .roles(UserRole.EMPLOYEE.name(), UserRole.MANAGER.name())
            .build();

    UserDetails susan =
        User.builder()
            .username("susan")
            .password("{noop}test123")
            .roles(UserRole.EMPLOYEE.name(), UserRole.MANAGER.name(), UserRole.ADMIN.name())
            .build();

    return new InMemoryUserDetailsManager(john, mary, susan);
  }
   */
}
