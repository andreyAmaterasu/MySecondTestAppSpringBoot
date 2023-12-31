package ru.kostromin.MySecondTestAppSpringBoot.service;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import ru.kostromin.MySecondTestAppSpringBoot.model.Response;
import ru.kostromin.MySecondTestAppSpringBoot.util.DateTimeUtil;

@Slf4j
public class ModifySystemTimeResponseService implements ModifyResponseService {

  @Override
  public Response modify(Response response) {

    log.info("Модификация поля systemTime в response");
    response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
    return response;
  }
}
