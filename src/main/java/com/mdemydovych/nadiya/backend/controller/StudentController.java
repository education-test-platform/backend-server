package com.mdemydovych.nadiya.backend.controller;

import com.mdemydovych.nadiya.backend.service.StudentService;
import com.mdemydovych.nadiya.model.examination.core.ExaminationPreview;
import com.mdemydovych.nadiya.model.examination.result.ExaminationResultDto;
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
@RequestMapping("/student")
public class StudentController {

  private final StudentService studentService;

  @GetMapping("/my/active/exams")
  public List<ExaminationPreview> myActiveExams() {
    return studentService.myActiveExams();
  }

  @GetMapping("/my/teachers")
  public List<UserDto> myTeachers() {
    return studentService.findMyTeachers();
  }

  @GetMapping("/me/assign/to/teacher/{teacherId}")
  public void assignMeToTeacher(@PathVariable String teacherId) {
    studentService.assignMeToTeacher(teacherId);
  }

  @PostMapping("/me/save/exam/result")
  public void meSaveExamResult(@RequestBody ExaminationResultDto resultDto) {
    studentService.saveExamResult(resultDto);
  }

  @GetMapping("/get/teachers")
  public List<UserDto> getTeachers() {
    return studentService.getTeachers();
  }

  @GetMapping("/my/all/exam/results")
  public List<ExaminationResultDto> findAllMyExamResults() {
    return studentService.findAllMyExamResults();
  }
}
