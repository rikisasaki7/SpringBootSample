package com.example.demo.aspect;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

  /** データベース関連の例外処理 */
  @ExceptionHandler(DataAccessException.class)
  public String handleDataAccessException(DataAccessException e, Model model) {

    model.addAttribute("error", "");
    model.addAttribute("message", "DataAccessExceptin発生");
    model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
    return "error";
  }

  /** その他の例外処理 */
  @ExceptionHandler(Exception.class)
  public String handleException(Exception e, Model model) {

    model.addAttribute("error", "");
    model.addAttribute("message", "予期しないエラーが発生しました");
    model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
    return "error";
  }

}
