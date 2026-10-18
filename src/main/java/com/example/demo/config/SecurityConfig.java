package com.example.demo.config;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // セキュリティ対象外の設定
    http
        .authorizeHttpRequests((authz) -> authz
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .requestMatchers("/login").permitAll()
            .requestMatchers("/user/signup").permitAll()
            .requestMatchers("/error").permitAll()
            .requestMatchers("/h2-console/**").permitAll()
            .requestMatchers("/admin").hasAuthority("ROLE_ADMIN")
            .anyRequest().authenticated())
        .formLogin(login -> login
            .loginPage("/login")
            .usernameParameter("userId")
            .passwordParameter("password")
            .defaultSuccessUrl("/user/list")
            .failureUrl("/login?error").permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout"))
        .rememberMe(remember -> remember
            .rememberMeParameter("remember-me")
            .tokenValiditySeconds(3600));
    // csrfを無効（一時的）
    // http.csrf(csrf -> csrf.disable());
    // ヘッダー設定
    http.headers(headers -> headers.frameOptions(option -> option.disable()));
    return http.build();
  }

  // インメモリ認証の時はこのBeanを有効にする
  // @Bean
  UserDetailsService userDetailService() {
    // 一般ユーザー
    UserDetails user = User.withDefaultPasswordEncoder()
        .username("user")
        .password("password")
        .roles("USER")
        .build();
    // 管理者ユーザー
    UserDetails admin = User.withDefaultPasswordEncoder()
        .username("admin")
        .password("password")
        .roles("USER", "ROLE_ADMIN")
        .build();
    return new InMemoryUserDetailsManager(user, admin);
  }
}
