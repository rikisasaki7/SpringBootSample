package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

  // @Bean
  // UserService userService(UserRepository repository, PasswordEncoder encoder) {
  // return new UserServiceImpl2(repository, encoder);
  // }
}
