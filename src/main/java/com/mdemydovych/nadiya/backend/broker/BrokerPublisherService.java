package com.mdemydovych.nadiya.backend.broker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdemydovych.nadiya.model.broker.BrokerMessage;
import io.github.centrifugal.centrifuge.Client;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrokerPublisherService {

  private static final Logger logger = LoggerFactory.getLogger(BrokerPublisherService.class);

  private final Client client;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @SneakyThrows
  public void sendMessage(String message, List<String> topics) {
    BrokerMessage brokerMessage = new BrokerMessage(message);
    byte[] data = objectMapper.writeValueAsBytes(brokerMessage);
    for (String topic : topics) {
      client.publish(topic, data, (throwable, publishResult) -> {
        logger.info(publishResult.toString());
      });
    }
  }

  private Map<String, Object> buildMessage(String message, List<String> topics) {
    Map<String, Object> body = new HashMap<>();
    body.put("method", "broadcast");
    body.put("params", Map.of("channels", topics, "data", new BrokerMessage(message)));
    return body;
  }

}
