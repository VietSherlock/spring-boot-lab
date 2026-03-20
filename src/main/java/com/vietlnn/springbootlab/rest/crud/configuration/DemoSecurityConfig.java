package com.vietlnn.springbootlab.rest.crud.configuration;

import com.vietlnn.springbootlab.rest.crud.entity.UserRole;
import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

  // user name
  private static final String JOHN = "john";
  private static final String MARY = "mary";
  private static final String SUSAN = "susan";

  // password
  private static final String NOOP_PWD = "{noop}test123";
  public static final String EMPLOYEE_PATH = "/api/employees";

  /**
   * Define user details with username, pwd, role, etc.
   *
   * <p>NOTE: Spring Boot will read the username and pwd here and ignore the one define in
   * properties file
   */
  @Bean
  public InMemoryUserDetailsManager userDetailsManager() {

    // Spring Security stores password as format: {id}encodedPassword
    // {id} encrypt type. "noop" -> plain text. "bcrypt" BCrypt pwd hashing
    UserDetails john =
        User.builder().username(JOHN).password(NOOP_PWD).roles(UserRole.EMPLOYEE.name()).build();

    UserDetails mary =
        User.builder()
            .username(MARY)
            .password(NOOP_PWD)
            .roles(UserRole.EMPLOYEE.name(), UserRole.MANAGER.name())
            .build();

    UserDetails susan =
        User.builder()
            .username(SUSAN)
            .password(NOOP_PWD)
            .roles(UserRole.EMPLOYEE.name(), UserRole.MANAGER.name(), UserRole.ADMIN.name())
            .build();

    return new InMemoryUserDetailsManager(john, mary, susan);
  }

  /**
   * Authorize employee endpoints per ROLE
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
}
