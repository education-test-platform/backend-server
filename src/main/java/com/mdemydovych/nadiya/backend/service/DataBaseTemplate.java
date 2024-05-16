package com.mdemydovych.nadiya.backend.service;

import com.mdemydovych.nadiya.backend.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
class DataBaseTemplate {

  private final RestTemplate databaseRestTemplate;

  public <T> T request(String url, HttpMethod method, Object body,
      Class<T> responseType, Object... params) {
    try {
      return databaseRestTemplate
          .exchange(url, method, new HttpEntity<>(body), responseType, params).getBody();
    } catch (Exception e) {
      throw new ServerException(e.getMessage());
    }
  }
}
