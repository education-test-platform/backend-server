package com.mdemydovych.nadiya.backend.service;

import com.mdemydovych.nadiya.backend.annotation.Notify;
import com.mdemydovych.nadiya.backend.config.properties.DatabaseProperties;
import com.mdemydovych.nadiya.backend.model.Event;
import com.mdemydovych.nadiya.model.request.AssignStudentRequest;
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

  @Notify(Event.ASSIGN_STUDENT)
  public void assignStudentToTeacher(AssignStudentRequest request) {
    template.request(properties.getAssignStudentToTeacherPath(),
        HttpMethod.POST, request, Void.class);
  }

  public List<UserDto> findStudentTeachers(String studentId) {
    return List.of(template.request(properties.getFindStudentTeachersPath(),
        HttpMethod.GET, null, UserDto[].class, studentId));
  }

  public List<UserDto> findTeacherStudents(String teacherId) {
    return List.of(template.request(properties.getFindTeacherStudentsPath(),
        HttpMethod.GET, null, UserDto[].class, teacherId));
  }
}
