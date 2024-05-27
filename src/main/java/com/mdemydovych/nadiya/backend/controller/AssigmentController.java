package com.mdemydovych.nadiya.backend.controller;

import com.mdemydovych.nadiya.backend.service.AssigmentService;
import com.mdemydovych.nadiya.model.user.UserDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assigment")
public class AssigmentController {

  private final AssigmentService assigmentService;

  @PostMapping("/find/student/teachers/{studentId}")
  public List<UserDto> findStudentTeachers(@PathVariable String studentId) {
    return assigmentService.findStudentTeachers(studentId);
  }

  @PostMapping("/find/teacher/students/{teacherId}")
  public List<UserDto> findTeacherStudents(@PathVariable String teacherId) {
    return assigmentService.findStudentTeachers(teacherId);
  }
}
