package ru.kostromin.MySecondTestAppSpringBoot.model;

import lombok.Getter;

@Getter
public enum ErrorMessages {

  EMPTY(""),
  VALIDATION("Ошибка валидации"),
  UNSUPPORTED("Произошла непредвиденная ошибка"),
  UNKNOWN("Не поддерживаемая ошибка");

  private final String description;

  ErrorMessages(String description) {
    this.description = description;
  }
}
