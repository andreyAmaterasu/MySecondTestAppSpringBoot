package ru.kostromin.MySecondTestAppSpringBoot.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.kostromin.MySecondTestAppSpringBoot.model.Positions;

class AnnualBonusServiceImplTest {

  @Test
  void calculate() {

    Positions position = Positions.HR;
    double bonus = 2.0;
    int workDays = 243;
    double salary = 100000.00;

    double result = new AnnualBonusServiceImpl().calculate(position, salary, bonus, workDays);

    double expected365 = 360493.8271604938;
    double expected366 = 361481.48148148146;
    Assertions.assertEquals(result, expected366);
  }
}