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

  /**
   * Уникальный идентификатор сообщение
   */
  @NotBlank(message = "uid не должен быть пустым")
  @Length(max = 32, message = "uid не больше 32 символов")
  private String uid;

  /**
   * Уникальный идентификатор операции
   */
  @NotBlank(message = "operationUid не должен быть пустым")
  @Length(max = 32, message = "operationUid не больше 32 символов")
  private String operationUid;

  /**
   * Имя системы отправителя
   */
  private Systems systemName;

  /**
   * Время создания сообщения
   */
  @NotBlank(message = "systemTime не должен быть пустым")
  private String systemTime;

  /**
   * Наименование ресурса
   */
  private String source;

  /**
   * Должность работника
   */
  private Positions position;

  /**
   * Заработная плата
   */
  private Double salary;

  /**
   * Премиальный коэффициент
   */
  private Double bonus;

  /**
   * Количество рабочих дней
   */
  private Integer workDays;

  /**
   * Уникальный идентификатор коммуникации
   */
  @Min(value = 1, message = "communicationId не должен быть меньше 1")
  @Max(value = 100000, message = "communicationId не должен быть больше 100000")
  private Integer communicationId;

  /**
   * Уникальный идентификатор шаблона
   */
  private Integer productCode;

  /**
   * Код продукта
   */
  private Integer templateId;

  /**
   * Смс код
   */
  private Integer smsCode;
}
