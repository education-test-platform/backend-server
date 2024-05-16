package com.mdemydovych.nadiya.backend.service;

import com.mdemydovych.nadiya.backend.annotation.Notify;
import com.mdemydovych.nadiya.backend.config.properties.DatabaseProperties;
import com.mdemydovych.nadiya.backend.mapper.ExaminationMapper;
import com.mdemydovych.nadiya.backend.model.Event;
import com.mdemydovych.nadiya.backend.model.ExaminationAo;
import com.mdemydovych.nadiya.model.examination.core.ExaminationDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExaminationService {

  private final DataBaseTemplate template;

  private final DatabaseProperties properties;

  private final ExaminationMapper mapper;

  @Notify(Event.PUBLISH_EXAM)
  public void save(ExaminationDto dto) {
    template.request(properties.getSaveExaminationPath(),
        HttpMethod.POST, dto, String.class);
  }

  public ExaminationDto findExamination(String id) {
    return template.request(properties.getFindExaminationPath(),
        HttpMethod.GET, null, ExaminationDto.class, id);
  }

  public ExaminationAo findExaminationPreview(String id) {
    ExaminationDto examination = template.request(properties.getFindExaminationPath(),
        HttpMethod.GET, null, ExaminationDto.class, id);
    return mapper.toPreview(examination);
  }

  public List<ExaminationAo> findTeacherExams(String teacherId) {
    List<ExaminationDto> result = List.of(
        template.request(properties.getFindTeacherExaminationPath(),
            HttpMethod.GET, null, ExaminationDto[].class, teacherId));
    return mapper.toPreview(result);
  }
}
