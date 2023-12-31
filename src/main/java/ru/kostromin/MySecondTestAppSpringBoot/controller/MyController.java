package ru.kostromin.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import ru.kostromin.MySecondTestAppSpringBoot.service.ModifyRequestService;
import ru.kostromin.MySecondTestAppSpringBoot.service.ModifyResponseService;
import ru.kostromin.MySecondTestAppSpringBoot.service.ValidationService;
import ru.kostromin.MySecondTestAppSpringBoot.util.DateTimeUtil;

@Slf4j
@RestController
public class MyController {

  private final ValidationService validationService;

  private final ModifyResponseService modifyResponseService;

  private final ModifyRequestService modifySystemNameRequestService;

  private final ModifyRequestService modifySourceRequestService;

  public MyController(ValidationService validationService,
      @Qualifier("modifySystemTimeResponseService") ModifyResponseService modifyResponseService,
      @Qualifier("modifySystemNameRequestService")
      ModifyRequestService modifySystemNameRequestService,
      @Qualifier("modifySourceRequestService")
      ModifyRequestService modifySourceRequestService) {

    this.validationService = validationService;
    this.modifyResponseService = modifyResponseService;
    this.modifySystemNameRequestService = modifySystemNameRequestService;
    this.modifySourceRequestService = modifySourceRequestService;
  }

  @PostMapping(value = "/feedback")
  public ResponseEntity<Response> feedback(
      @Valid @RequestBody Request request, BindingResult bindingResult) {

    log.info("request: {}", request);

    log.info("Обновление поля systemTime для расчета разницы времени между запросами");
    request.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));

    Response response = Response.builder()
        .uid(request.getUid())
        .operationUid(request.getOperationUid())
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

    modifyResponseService.modify(response);
    modifySystemNameRequestService.modify(request);
    modifySourceRequestService.modify(request);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
