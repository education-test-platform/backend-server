package com.mdemydovych.nadiya.backend.mapper;

import com.mdemydovych.nadiya.model.examination.core.ExaminationDto;
import com.mdemydovych.nadiya.model.examination.core.ExaminationPreview;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ExaminationMapper {

  public List<ExaminationPreview> toPreview(List<ExaminationDto> examination) {
    List<ExaminationPreview> result = new ArrayList<>();
    for (ExaminationDto examinationDto : examination) {
      result.add(toPreview(examinationDto));
    }
    return result;
  }

  public ExaminationPreview toPreview(ExaminationDto examination) {
    ExaminationPreview examinationPreview = new ExaminationPreview();
    examinationPreview.setId(examination.getId());
    examinationPreview.setTitle(examination.getTitle());
    examinationPreview.setEnabled(examination.isEnabled());
    examinationPreview.setTeacher(examination.getTeacher());
    return examinationPreview;
  }
}
