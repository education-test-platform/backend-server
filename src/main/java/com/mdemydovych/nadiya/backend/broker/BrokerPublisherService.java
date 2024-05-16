package com.mdemydovych.nadiya.backend.broker;

import io.github.centrifugal.centrifuge.Client;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrokerPublisherService {

  private static final Logger logger = LoggerFactory.getLogger(BrokerPublisherService.class);

  private final Client client;

  public void sendMessage(String message, String topic) {
    client.publish(topic, message.getBytes(), (error, result) -> {
      if (Objects.nonNull(result)) {
        logger.error("Error while send message topic - {}", topic);
      }
    });
  }

}
