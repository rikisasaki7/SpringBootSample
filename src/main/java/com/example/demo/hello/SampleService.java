package com.example.demo.hello;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

  @Autowired
  private SampleRepository repository;

  public Sample getSample(String id) {
    // 検索
    Optional<Sample> optSample = repository.findById(id);
    Sample sample = optSample.get();
    return sample;
  }
}
