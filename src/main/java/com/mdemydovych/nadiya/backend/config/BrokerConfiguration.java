package com.mdemydovych.nadiya.backend.config;

import com.mdemydovych.nadiya.backend.listener.ServerBrokerListener;
import io.github.centrifugal.centrifuge.Client;
import io.github.centrifugal.centrifuge.Options;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrokerConfiguration {

  @Bean
  public Client client(@Value("${app.broker.url}") String brokerUrl) {
    Options opts = new Options();
    Client client = new Client(brokerUrl, opts, new ServerBrokerListener());
    client.connect();
    return client;
  }

}
