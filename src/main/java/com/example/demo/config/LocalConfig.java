package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class LocalConfig {

  // @Bean
  // UserService userService(UserMapper mapper, PasswordEncoder encoder) {
  // return new UserServiceImpl(mapper, encoder);
  // }

}
