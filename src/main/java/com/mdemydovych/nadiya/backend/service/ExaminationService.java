package com.mdemydovych.nadiya.backend.service;

import com.mdemydovych.nadiya.backend.annotation.Notify;
import com.mdemydovych.nadiya.backend.config.properties.DatabaseProperties;
import com.mdemydovych.nadiya.backend.mapper.ExaminationMapper;
import com.mdemydovych.nadiya.backend.model.Event;
import com.mdemydovych.nadiya.model.examination.core.ExaminationAo;
import com.mdemydovych.nadiya.model.examination.core.ExaminationDto;
import com.mdemydovych.nadiya.model.examination.core.ExaminationPreview;
import com.mdemydovych.nadiya.model.examination.core.QuestionDto;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExaminationService {

  private final DataBaseTemplate template;

  private final DatabaseProperties properties;

  private final ExaminationMapper mapper;

  @Notify(Event.PUBLISH_EXAM)
  @PreAuthorize("hasAuthority('TEACHER') "
      + "and T(com.mdemydovych.nadiya.backend.utils.UserUtils).getCurrentUserId() == #dto.teacher.id")
  public void save(ExaminationDto dto) {
    template.request(properties.getSaveExaminationPath(),
        HttpMethod.POST, dto, String.class);
  }

  @PostAuthorize("returnObject.enabled")
  public ExaminationAo findExaminationAo(String id) {
    return template.request(properties.getFindExaminationPath(),
        HttpMethod.GET, null, ExaminationAo.class, id);
  }

  public Set<QuestionDto> findExaminationQuestions(String id) {
    return template.request(properties.getFindExaminationPath(),
        HttpMethod.GET, null, ExaminationDto.class, id).getQuestions();
  }

  public ExaminationPreview findExaminationPreview(String id) {
    ExaminationDto examination = findExaminationById(id);
    return mapper.toPreview(examination);
  }

  public List<ExaminationPreview> findTeacherExams(String teacherId) {
    List<ExaminationDto> result = List.of(
        template.request(properties.getFindTeacherExaminationPath(),
            HttpMethod.GET, null, ExaminationDto[].class, teacherId));
    return mapper.toPreview(result);
  }

  public List<ExaminationPreview> findActiveStudentExams(String studentId) {
    List<ExaminationDto> result = List.of(
        template.request(properties.getFindActiveStudentExams(),
            HttpMethod.GET, null, ExaminationDto[].class, studentId));
    return mapper.toPreview(result);
  }

  public ExaminationDto findExaminationById(String id) {
    return template.request(properties.getFindExaminationPath(),
        HttpMethod.GET, null, ExaminationDto.class, id);
  }
}
