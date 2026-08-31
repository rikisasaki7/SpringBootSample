package com.example.demo.user.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignupForm {
  @NotBlank(message = "ユーザーIDは必須です")
  private String userId;

  @NotBlank(message = "パスワードは必須です")
  private String password;

  @NotBlank(message = "ユーザー名は必須です")
  private String userName;

  @NotNull(message = "誕生日は必須です")
  @DateTimeFormat(pattern = "yyyy/MM/dd")
  private Date birthday;

  @NotNull(message = "年齢は必須です")
  private Integer age;

  @NotNull(message = "性別は必須です")
  private String gender;
}