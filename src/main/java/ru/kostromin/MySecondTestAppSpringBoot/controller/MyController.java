package ru.kostromin.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kostromin.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.kostromin.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.kostromin.MySecondTestAppSpringBoot.model.Request;
import ru.kostromin.MySecondTestAppSpringBoot.model.Response;
import ru.kostromin.MySecondTestAppSpringBoot.service.ValidationService;

@RestController
@AllArgsConstructor
public class MyController {

  private final ValidationService validationService;

  @PostMapping(value = "/feedback")
  public ResponseEntity<Response> feedback(
      @Valid @RequestBody Request request, BindingResult bindingResult) {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    Response response = Response.builder()
        .uid(request.getUid())
        .operationUid(request.getOperationUid())
        .systemTime(simpleDateFormat.format(new Date()))
        .code("success")
        .errorCode("")
        .errorMessage("")
        .build();

    try {
      validationService.isValid(bindingResult);
    } catch (ValidationFailedException | UnsupportedCodeException e) {
      response.setCode("failed");
      response.setErrorCode(e.getClass().getSimpleName());
      response.setErrorMessage(bindingResult.getAllErrors()
          .stream()
          .map(ObjectError::getDefaultMessage)
          .collect(Collectors.joining(", ")));
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      response.setCode("failed");
      response.setErrorCode("UnknownException");
      response.setErrorMessage("Произошла непредвиденная ошибка");
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
