package com.mdemydovych.nadiya.backend.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.database")
public class DatabaseProperties {

  private String saveExaminationPath = "/api/examination/save";

  private String findExaminationPath = "/api/examination/find/{id}";

  private String findTeacherExaminationPath = "/api/examination/find/teacher/{teacherId}";

  private String assignStudentToTeacherPath = "/api/assigment/assign/student/to/teacher";

  private String findStudentTeachersPath = "/api/assigment/find/student/teachers/{studentId}";

  private String findTeacherStudentsPath = "/api/assigment/find/teacher/students/{teacherId}";

  private String saveExamResultPath = "/api/examination/result/save";

  private String findExamResultPath = "/api/examination/result/find/{studentId}/{examId}";

  private String findActiveStudentExams = "/api/examination/find/active/student/{id}";

  private String findUserByRolePath = "/api/user/find/by/role?role=%s";

  private String findStudentExamResults = "/api/examination/result/find/{studentId}";

  private String findAllExamResultsPath = "/api/examination/result/find/all/{examId}";

}
