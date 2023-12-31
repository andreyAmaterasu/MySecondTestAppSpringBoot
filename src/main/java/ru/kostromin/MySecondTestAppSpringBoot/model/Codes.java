package ru.kostromin.MySecondTestAppSpringBoot.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Codes {

  SUCCESS("success"),
  FAILED("failed");

  private final String name;

  Codes(String name) {
    this.name = name;
  }
}
