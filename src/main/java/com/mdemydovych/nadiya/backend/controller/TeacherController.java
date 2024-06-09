package com.mdemydovych.nadiya.backend.controller;

import com.mdemydovych.nadiya.backend.service.TeacherService;
import com.mdemydovych.nadiya.model.examination.core.ExaminationDto;
import com.mdemydovych.nadiya.model.examination.core.ExaminationPreview;
import com.mdemydovych.nadiya.model.user.UserDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {

  private final TeacherService teacherService;

  @PostMapping("/me/save/examination")
  public void saveExamination(@RequestBody ExaminationDto examination) {
    teacherService.meSaveExamination(examination);
  }

  @GetMapping("/my/students")
  public List<UserDto> findMyStudents() {
    return teacherService.findMyStudents();
  }

  @GetMapping("/my/examinations")
  public List<ExaminationPreview> findMyExaminations() {
    return teacherService.findMyExaminations();
  }

  @PostMapping("/me/change/exam/status/{examId}")
  public void activateExam(@PathVariable String examId) {
    teacherService.changeExamStatus(examId);
  }

}
