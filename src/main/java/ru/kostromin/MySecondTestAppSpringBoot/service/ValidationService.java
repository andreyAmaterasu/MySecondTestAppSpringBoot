package ru.kostromin.MySecondTestAppSpringBoot.service;

import org.springframework.validation.BindingResult;
import ru.kostromin.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.kostromin.MySecondTestAppSpringBoot.exception.ValidationFailedException;

public interface ValidationService {

  void isValid(BindingResult bindingResult) throws ValidationFailedException, UnsupportedCodeException;
}
