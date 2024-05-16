package com.mdemydovych.nadiya.backend.service;

import com.mdemydovych.nadiya.backend.config.properties.DatabaseProperties;
import com.mdemydovych.nadiya.model.user.UserDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssigmentService {

  public final DataBaseTemplate template;

  public final DatabaseProperties properties;

  public void assignStudentToTeacher(String studentId, String teacherId) {
    template.request(properties.getAssignStudentToTeacherPath(),
        HttpMethod.POST, null, Void.class, teacherId, studentId);
  }

  public List<UserDto> findStudentTeachers(String studentId) {
    return List.of(template.request(properties.getFindStudentTeachersPath(),
        HttpMethod.POST, null, UserDto[].class, studentId));
  }

  public List<UserDto> findTeacherStudents(String teacherId) {
    return List.of(template.request(properties.getFindTeacherStudentsPath(),
        HttpMethod.POST, null, UserDto[].class, teacherId));
  }
}
