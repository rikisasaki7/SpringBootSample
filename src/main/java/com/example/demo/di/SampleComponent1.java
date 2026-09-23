package com.example.demo.di;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

@Component("SampleComponent1")
@Slf4j
public class SampleComponent1 implements SampleComponent {

  private String str = "Sample1";

  @Override
  public String getStr() {
    return this.str;
  }

  @PostConstruct
  public void init() {
    log.info(str + "init");
  }

  @PreDestroy
  public void preDestroy() {
    log.info(str + "preDestroy");
  }
}
