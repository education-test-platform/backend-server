package com.mdemydovych.nadiya.backend.model;

import com.mdemydovych.nadiya.model.examination.core.ExaminationDto;
import com.mdemydovych.nadiya.model.request.AssignStudentRequest;
import java.util.function.Function;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Event {
  PUBLISH_EXAM(o -> ((ExaminationDto) o).getTeacher().getId()),
  ASSIGN_STUDENT(o -> ((AssignStudentRequest) o).teacherId());

  private final Function<Object, String> extractPoint;
}
