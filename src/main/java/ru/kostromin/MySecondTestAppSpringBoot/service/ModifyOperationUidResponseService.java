package ru.kostromin.MySecondTestAppSpringBoot.service;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kostromin.MySecondTestAppSpringBoot.model.Response;

@Slf4j
@Service
public class ModifyOperationUidResponseService implements ModifyResponseService {

  @Override
  public Response modify(Response response) {

    log.info("Модификация поля operationUid в response");
    UUID uuid = UUID.randomUUID();
    response.setOperationUid(uuid.toString());
    return response;
  }
}
