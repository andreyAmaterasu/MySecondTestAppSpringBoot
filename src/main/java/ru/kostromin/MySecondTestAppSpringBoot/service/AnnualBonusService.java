package ru.kostromin.MySecondTestAppSpringBoot.service;

import ru.kostromin.MySecondTestAppSpringBoot.model.Positions;

public interface AnnualBonusService {

  double calculate(Positions position, double salary, double bonus, int workDays);
}
