package com.example.demo.user.domain.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class LoginUser extends User {

  private String displayUserName;

  public LoginUser(
      String username,
      String password,
      Collection<? extends GrantedAuthority> authorities,
      String displayUserName) {
    super(username, password, authorities);
    this.displayUserName = displayUserName;
  }
}
