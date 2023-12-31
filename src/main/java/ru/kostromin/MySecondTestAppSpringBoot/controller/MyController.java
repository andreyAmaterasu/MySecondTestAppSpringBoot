package ru.kostromin.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kostromin.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.kostromin.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.kostromin.MySecondTestAppSpringBoot.model.Codes;
import ru.kostromin.MySecondTestAppSpringBoot.model.ErrorCodes;
import ru.kostromin.MySecondTestAppSpringBoot.model.ErrorMessages;
import ru.kostromin.MySecondTestAppSpringBoot.model.Request;
import ru.kostromin.MySecondTestAppSpringBoot.model.Response;
import ru.kostromin.MySecondTestAppSpringBoot.service.ValidationService;
import ru.kostromin.MySecondTestAppSpringBoot.util.DateTimeUtil;

@Slf4j
@RestController
@AllArgsConstructor
public class MyController {

  private final ValidationService validationService;

  @PostMapping(value = "/feedback")
  public ResponseEntity<Response> feedback(
      @Valid @RequestBody Request request, BindingResult bindingResult) {

    log.info("request: {}", request);

    Response response = Response.builder()
        .uid(request.getUid())
        .operationUid(request.getOperationUid())
        .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
        .code(Codes.SUCCESS)
        .errorCode(ErrorCodes.EMPTY)
        .errorMessage(ErrorMessages.EMPTY)
        .build();

    try {
      validationService.isValid(bindingResult);
    } catch (ValidationFailedException | UnsupportedCodeException e) {
      log.error("Ошибка валидации, установка значений для полей code, errorCode и errorMessage");
      response.setCode(Codes.FAILED);
      response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
      response.setErrorMessage(ErrorMessages.VALIDATION);
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      log.error("Непредвиденная ошибка, установка значений для полей code, errorCode и errorMessage");
      response.setCode(Codes.FAILED);
      response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
      response.setErrorMessage(ErrorMessages.UNKNOWN);
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
