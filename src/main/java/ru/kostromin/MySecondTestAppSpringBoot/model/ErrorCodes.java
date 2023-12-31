package ru.kostromin.MySecondTestAppSpringBoot.model;

import lombok.Getter;

@Getter
public enum ErrorCodes {

  EMPTY(""),
  VALIDATION_EXCEPTION("ValidationException"),
  UNKNOWN_EXCEPTION("UnknownException"),
  UNSUPPORTED_EXCEPTION("UnsupportedException");

  private final String name;

  ErrorCodes(String name) {
    this.name = name;
  }
}
