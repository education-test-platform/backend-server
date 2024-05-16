package com.mdemydovych.nadiya.backend.config;

import com.mdemydovych.nadiya.backend.config.properties.DatabaseProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties({DatabaseProperties.class})
public class AppConfiguration {

  private static final String KEY_HEADER_NAME = "secretKey";

  @Bean
  public RestTemplate databaseRestTemplate(@Value("${app.database.url}") String databaseUrl,
      @Value("${app.database.secretKey}") String databaseKey) {
    return new RestTemplateBuilder()
        .rootUri(databaseUrl)
        .defaultHeader(KEY_HEADER_NAME, databaseKey)
        .build();
  }
}
