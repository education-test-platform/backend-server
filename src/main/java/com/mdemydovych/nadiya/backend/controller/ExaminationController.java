package com.mdemydovych.nadiya.backend.controller;

import com.mdemydovych.nadiya.backend.service.ExaminationService;
import com.mdemydovych.nadiya.model.examination.core.ExaminationAo;
import com.mdemydovych.nadiya.model.examination.core.ExaminationDto;
import com.mdemydovych.nadiya.model.examination.core.ExaminationPreview;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("examination")
public class ExaminationController {

  private final ExaminationService examinationService;

  @GetMapping("/save")
  public void save(@RequestBody ExaminationDto examinationDto) {
    examinationService.save(examinationDto);
  }

  @GetMapping("/preview/{id}")
  public ExaminationPreview getExaminationPreview(@PathVariable String id) {
    return examinationService.findExaminationPreview(id);
  }

  @GetMapping("/{id}")
  public ExaminationAo getExamination(@PathVariable String id) {
    return examinationService.findExaminationAo(id);
  }

  @GetMapping("/teacher/exams/{teacherId}")
  public List<ExaminationPreview> findTeacherExams(@PathVariable String teacherId) {
    return examinationService.findTeacherExams(teacherId);
  }
}
