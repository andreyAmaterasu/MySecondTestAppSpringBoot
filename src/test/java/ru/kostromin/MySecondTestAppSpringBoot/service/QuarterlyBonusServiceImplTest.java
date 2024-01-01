package ru.kostromin.MySecondTestAppSpringBoot.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.kostromin.MySecondTestAppSpringBoot.model.Positions;

class QuarterlyBonusServiceImplTest {

  @Test
  void calculateManager() {

    Positions position = Positions.PM;
    double bonus = 2.0;
    double salary = 100000.00;

    double result = new QuarterlyBonusServiceImpl().calculate(position, salary, bonus);

    double expected = 2000.0;
    Assertions.assertEquals(result, expected);
  }

  @Test
  void calculateNotManager() {

    Positions position = Positions.TEST;
    double bonus = 2.0;
    double salary = 100000.00;

    double result = new QuarterlyBonusServiceImpl().calculate(position, salary, bonus);

    double expected = -1;
    Assertions.assertEquals(result, expected);
  }
}