package ru.kostromin.MySecondTestAppSpringBoot.service;

import ru.kostromin.MySecondTestAppSpringBoot.model.Positions;

public class QuarterlyBonusServiceImpl implements QuarterlyBonusService {

  @Override
  public double calculate(Positions position, double salary, double bonus) {

    return position.isManager() ? salary * bonus / 100 : -1;
  }
}
