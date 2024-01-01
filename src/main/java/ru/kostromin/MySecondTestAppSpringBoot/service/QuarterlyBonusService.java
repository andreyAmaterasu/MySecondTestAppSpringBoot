package ru.kostromin.MySecondTestAppSpringBoot.service;

import ru.kostromin.MySecondTestAppSpringBoot.model.Positions;

public interface QuarterlyBonusService {

  double calculate(Positions position, double salary, double bonus);
}
