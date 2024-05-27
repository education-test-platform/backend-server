package com.mdemydovych.nadiya.backend.broker.handler;

import com.mdemydovych.nadiya.backend.model.Event;
import com.mdemydovych.nadiya.backend.service.AssigmentService;
import com.mdemydovych.nadiya.model.user.UserDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TopicHandlerService {

  private final AssigmentService assigmentService;

  public List<String> handleTopics(Object var, Event event) {
    String mainPoint = event.getExtractPoint().apply(var);
    switch (event) {
      case PUBLISH_EXAM -> {
        return assigmentService.findTeacherStudents(mainPoint)
            .stream()
            .map(UserDto::getId)
            .toList();
      }
      case ASSIGN_STUDENT -> {
        return List.of(mainPoint);
      }
    }
    return List.of();
  }
}
