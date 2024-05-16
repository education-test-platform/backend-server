package com.mdemydovych.nadiya.backend.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.database")
public class DatabaseProperties {

  private String saveExaminationPath = "/api/examination/save";

  private String findExaminationPath = "/api/examination/find/{id}";

  private String findTeacherExaminationPath = "/api/examination/find/teacher/{teacherId}";

  private String assignStudentToTeacherPath = "/api/assigment/assign/student/to/teacher/{teacherId}/{studentId}";

  private String findStudentTeachersPath = "/api/assigment/find/student/teachers/{studentId}";

  private String findTeacherStudentsPath = "/api/assigment/find/teacher/students/{teacherId}";

  private String saveExamResultPath = "/api/examination/result/save";

  private String findExamResultPath = "/api/examination/result/find/{studentId}/{examId}";

}
