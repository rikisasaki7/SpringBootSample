package com.example.demo.user.form;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.validator.BirthdayAge;
import com.example.demo.validator.LengthMin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@BirthdayAge(birthdayFieldName = "birthday", ageFieldName = "age")
public class SignupForm {
  // @NotBlank(message = "ユーザーIDは必須です")
  @NotBlank
  @Email
  @LengthMin(min = 5)
  private String userId;

  @NotEmpty
  @Length(min = 8, max = 20)
  @Pattern(regexp = "^[a-zA-Z0-9]+$")
  private String password;

  // @NotBlank(message = "ユーザー名は必須です")
  @NotBlank
  @LengthMin(min = 3)
  private String userName;

  // @NotNull(message = "誕生日は必須です")
  @NotNull
  @DateTimeFormat(pattern = "yyyy/MM/dd")
  private Date birthday;

  @Max(100)
  @Min(20)
  private Integer age;

  // @NotNull(message = "性別は必須です")
  @NotNull
  private String gender;
}