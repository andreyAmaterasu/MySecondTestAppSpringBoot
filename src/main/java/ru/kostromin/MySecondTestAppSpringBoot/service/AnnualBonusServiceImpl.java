package ru.kostromin.MySecondTestAppSpringBoot.service;

import java.time.LocalDate;
import ru.kostromin.MySecondTestAppSpringBoot.model.Positions;

public class AnnualBonusServiceImpl implements AnnualBonusService {

  @Override
  public double calculate(Positions position, double salary, double bonus, int workDays) {

    return salary * bonus * LocalDate.now().lengthOfYear() * position.getPositionCoefficient() / workDays;
  }
}
