package com.mdemydovych.nadiya.backend.service;

import com.mdemydovych.nadiya.backend.utils.UserUtils;
import com.mdemydovych.nadiya.model.examination.core.ExaminationPreview;
import com.mdemydovych.nadiya.model.examination.result.ExaminationResultDto;
import com.mdemydovych.nadiya.model.request.AssignStudentRequest;
import com.mdemydovych.nadiya.model.user.UserDto;
import com.mdemydovych.nadiya.model.user.UserRole;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final ExaminationService examinationService;

  private final ExaminationResultService resultService;

  private final AssigmentService assigmentService;

  private final UserService userService;

  public List<ExaminationPreview> myActiveExams() {
    return examinationService.findActiveStudentExams(UserUtils.getCurrentUserId());
  }

  public List<UserDto> findMyTeachers() {
    return assigmentService.findStudentTeachers(UserUtils.getCurrentUserId());
  }

  public void assignMeToTeacher(String teacherId) {
    assigmentService.assignStudentToTeacher(AssignStudentRequest.builder()
        .teacherId(teacherId)
        .studentId(UserUtils.getCurrentUserId())
        .build());
  }

  public void saveExamResult(ExaminationResultDto result) {
    ExaminationResultDto toSave = prepareResult(result);
    resultService.save(toSave);
  }

  private ExaminationResultDto prepareResult(ExaminationResultDto toPrepare) {
    UserDto user = new UserDto();
    user.setId(UserUtils.getCurrentUserId());
    toPrepare.setStudent(user);
    return toPrepare;
  }

  public List<UserDto> getTeachers() {
    return userService.findUsersByRole(UserRole.TEACHER);
  }

  public List<ExaminationResultDto> findAllMyExamResults() {
    return resultService.findStudentExamResults(UserUtils.getCurrentUserId());
  }
}
