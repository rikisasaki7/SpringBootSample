package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.demo.user.domain.model.LoginUser;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {

  @Pointcut("execution(* *..*UserService*.*(..))")
  public void userService() {
  }

  @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
  public void getMapping() {
  }

  @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
  public void postMapping() {
  }

  @Before("userService()")
  public void startLog(JoinPoint jp) {
    log.info("method start: {}", jp.getSignature());
  }

  @After("userService()")
  public void endLog(JoinPoint jp) {
    log.info("method end: {}", jp.getSignature());
  }

  @Around("getMapping() || postMapping()")
  public Object StartLog(ProceedingJoinPoint jp) throws Throwable {

    // 認証情報取得
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userId = authentication.getName();
    String authClassName = authentication.getPrincipal().getClass().getSimpleName();
    if ("LoginUser".equals(authClassName)) {
      LoginUser loginUser = (LoginUser) authentication.getPrincipal();
      log.info("ユーザーID: {}, ユーザー名: {}", loginUser.getUsername(), loginUser.getDisplayUserName());
    }
    log.info("ユーザーID: {}, method start(controller): {}", userId, jp.getSignature());

    try {
      Object result = jp.proceed();
      log.info("ユーザーID: {}, method end(controller): {}", userId, jp.getSignature());
      return result;
    } catch (Exception e) {
      log.error("ユーザーID: {}, method error(controller): {}", userId, jp.getSignature());
      throw e;
    }
  }
}
