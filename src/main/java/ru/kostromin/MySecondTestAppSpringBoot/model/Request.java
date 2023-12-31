package ru.kostromin.MySecondTestAppSpringBoot.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

  @NotBlank(message = "uid не должен быть пустым")
  @Length(max = 32, message = "uid не больше 32 символов")
  private String uid;

  @NotBlank(message = "operationUid не должен быть пустым")
  @Length(max = 32, message = "operationUid не больше 32 символов")
  private String operationUid;

  private Systems systemName;

  @NotBlank(message = "systemTime не должен быть пустым")
  private String systemTime;

  private String source;

  @Min(value = 1, message = "communicationId не должен быть меньше 1")
  @Max(value = 100000, message = "communicationId не должен быть больше 100000")
  private int communicationId;

  private int productCode;

  private int templateId;

  private int smsCode;
}
