package com.mdemydovych.nadiya.backend.mapper;

import com.mdemydovych.nadiya.backend.model.ExaminationAo;
import com.mdemydovych.nadiya.model.examination.core.ExaminationDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ExaminationMapper {

  public List<ExaminationAo> toPreview(List<ExaminationDto> examination) {
    List<ExaminationAo> result = new ArrayList<>();
    for (ExaminationDto examinationDto : examination) {
      result.add(toPreview(examinationDto));
    }
    return result;
  }

  public ExaminationAo toPreview(ExaminationDto examination) {
    ExaminationAo examinationAo = new ExaminationAo();
    examinationAo.setId(examination.getId());
    examinationAo.setTitle(examination.getTitle());
    examinationAo.setTeacherId(examination.getTeacherId());
    return examinationAo;
  }
}
