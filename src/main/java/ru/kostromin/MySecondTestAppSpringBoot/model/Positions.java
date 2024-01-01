package ru.kostromin.MySecondTestAppSpringBoot.model;

import lombok.Getter;

@Getter
public enum Positions {

  DEV(2.2, false),
  HR(1.2, false),
  TL(2.6, true),
  TEST(2.1, false),
  PM(1.8, true),
  AL(2.0, false);

  private final double positionCoefficient;

  private final boolean isManager;

  Positions(double positionCoefficient, boolean isManager) {
    this.positionCoefficient = positionCoefficient;
    this.isManager = isManager;
  }
}
