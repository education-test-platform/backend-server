package com.mdemydovych.nadiya.backend.service;

import com.mdemydovych.nadiya.backend.config.properties.DatabaseProperties;
import com.mdemydovych.nadiya.model.user.UserDto;
import com.mdemydovych.nadiya.model.user.UserRole;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final DataBaseTemplate template;

  private final DatabaseProperties properties;

  public List<UserDto> findUsersByRole(UserRole role) {
    return List.of(
        template.request(String.format(properties.getFindUserByRolePath(), role),
            HttpMethod.GET, null, UserDto[].class));
  }
}
