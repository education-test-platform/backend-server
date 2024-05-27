package com.mdemydovych.nadiya.backend.controller;

import com.mdemydovych.nadiya.backend.service.ExaminationResultService;
import com.mdemydovych.nadiya.model.examination.result.ExaminationResultDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/examination/result")
public class ExaminationResultController {

  private final ExaminationResultService resultService;

  @PostMapping("/save")
  public void save(@RequestBody ExaminationResultDto result) {
    resultService.save(result);
  }

  @GetMapping("/find/{examId}/{studentId}")
  public ExaminationResultDto findExamResult(
      @PathVariable String examId, @PathVariable String studentId) {
    return resultService.findExamResult(examId, studentId);
  }

  @GetMapping("/find/{examId}")
  public List<ExaminationResultDto> findAllExamResults(@PathVariable String examId) {
    return resultService.findAllExamResults(examId);
  }
}
