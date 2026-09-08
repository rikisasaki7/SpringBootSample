package com.example.demo.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LengthMinValidator implements ConstraintValidator<LengthMin, String> {

  /** 文字列の長さ */
  private int minLength;

  /** 初期化処理 */
  @Override
  public void initialize(LengthMin lengthMin) {
    this.minLength = lengthMin.min();
  }

  /** チェック処理 */
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || value.isEmpty()) {
      return true; // nullまたは空文字は有効とする（@NotNullでチェックする）
    }

    // 文字数チェック
    return value.length() >= minLength;
  }
}
