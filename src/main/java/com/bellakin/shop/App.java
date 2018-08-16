package com.bellakin.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(
  scanBasePackages = "com.bellakin.*",
  exclude = {
    MongoAutoConfiguration.class, MongoDataAutoConfiguration.class
  })
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

}
