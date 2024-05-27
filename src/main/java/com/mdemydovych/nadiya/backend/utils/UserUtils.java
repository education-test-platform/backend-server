package com.mdemydovych.nadiya.backend.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class UserUtils {

  private UserUtils() {

  }

  public static String getCurrentUserId() {
    return ((Jwt) SecurityContextHolder.getContext()
        .getAuthentication().getPrincipal()).getClaim("userId");
  }
}
