package com.mdemydovych.nadiya.backend.service;

import com.mdemydovych.nadiya.backend.utils.UserUtils;
import com.mdemydovych.nadiya.model.examination.core.ExaminationDto;
import com.mdemydovych.nadiya.model.examination.core.ExaminationPreview;
import com.mdemydovych.nadiya.model.user.UserDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

  private final ExaminationService examinationService;

  private final AssigmentService assigmentService;

  public List<UserDto> findMyStudents() {
    return assigmentService.findTeacherStudents(UserUtils.getCurrentUserId());
  }

  public void meSaveExamination(ExaminationDto examination) {
    examination.setTeacher(prepareCurrentUser());
    examinationService.save(examination);
  }

  public List<ExaminationPreview> findMyExaminations() {
    return examinationService.findTeacherExams(UserUtils.getCurrentUserId());
  }

  private UserDto prepareCurrentUser() {
    UserDto user = new UserDto();
    user.setId(UserUtils.getCurrentUserId());
    return user;
  }
}
