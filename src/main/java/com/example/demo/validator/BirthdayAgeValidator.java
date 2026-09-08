package com.example.demo.validator;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.ConstraintValidator;

public class BirthdayAgeValidator implements ConstraintValidator<BirthdayAge, Object> {

  /** 誕生日のフィールド名 */
  private String birthdayFieldName;
  /** 年齢のフィールド名 */
  private String ageFieldName;
  /** メッセージ */
  private String message;

  @Override
  public void initialize(BirthdayAge birthdayAge) {
    this.birthdayFieldName = birthdayAge.birthdayFieldName();
    this.ageFieldName = birthdayAge.ageFieldName();
    this.message = birthdayAge.message();
  }

  @Override
  public boolean isValid(Object value, jakarta.validation.ConstraintValidatorContext context) {

    // 値の取得
    BeanWrapper beanWrapper = new BeanWrapperImpl(value);
    Date birthday = (Date) beanWrapper.getPropertyValue(this.birthdayFieldName);
    Integer age = (Integer) beanWrapper.getPropertyValue(this.ageFieldName);

    // 空の場合は@NotNullなどでチェックする
    if (birthday == null || age == null) {
      return true;
    }

    // 誕生日から年齢を算出
    int calculatedAge = calculateAge(birthday);
    // 算出した年齢と入力された年齢が一致するかチェック
    if (calculatedAge != age) {
      // エラーメッセージ
      // String errorMessage = "誕生日と年齢があっていません。入力値：" + age + "歳";
      // エラーメッセージの出力フィールド設定
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(this.message)
          // context.buildConstraintViolationWithTemplate(errorMessage)
          // .addPropertyNode(this.birthdayFieldName)
          .addConstraintViolation();
      return false;
    }
    return true;
  }

  /**
   * 誕生日から年齢を算出する
   */
  private int calculateAge(Date birthday) {
    LocalDate now = LocalDate.now();
    LocalDate birthdayLocalDate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    Period period = Period.between(birthdayLocalDate, now);
    return period.getYears();
  }
}
