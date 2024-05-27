package com.mdemydovych.nadiya.backend.service;

import com.mdemydovych.nadiya.backend.config.properties.DatabaseProperties;
import com.mdemydovych.nadiya.model.examination.core.QuestionDto;
import com.mdemydovych.nadiya.model.examination.result.AnswerDto;
import com.mdemydovych.nadiya.model.examination.result.ExaminationResultDto;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExaminationResultService {

  private final DataBaseTemplate template;

  private final DatabaseProperties properties;

  private final ExaminationService examinationService;

  public void save(ExaminationResultDto resultDto) {
    ExaminationResultDto toSave = calculateAndSetScore(resultDto);
    template.request(properties.getSaveExamResultPath(), HttpMethod.POST, toSave, Void.class);
  }

  public ExaminationResultDto findExamResult(String examId, String studentId) {
    return template.request(properties.getSaveExamResultPath(), HttpMethod.GET, null,
        ExaminationResultDto.class, examId, studentId);
  }

  public List<ExaminationResultDto> findStudentExamResults(String studentId) {
    return List.of(template.request(properties.getFindStudentExamResults(), HttpMethod.GET,
        null, ExaminationResultDto[].class, studentId));
  }

  public List<ExaminationResultDto> findAllExamResults(String examId) {
    return List.of(template.request(properties.getFindAllExamResultsPath(), HttpMethod.GET,
        null, ExaminationResultDto[].class, examId));
  }

  private ExaminationResultDto calculateAndSetScore(ExaminationResultDto resultDto) {
    Set<QuestionDto> questions = examinationService
        .findExaminationQuestions(resultDto.getExamination().getId());
    int score = (int) calculateCorrectAnswers(questions, resultDto);
    resultDto.setScore(String.valueOf((double) score / questions.size() * 100));
    return resultDto;
  }

  private long calculateCorrectAnswers(
      Set<QuestionDto> examQuestions, ExaminationResultDto resultDto) {
    Map<String, QuestionDto> questions = collectQuestionsById(examQuestions);
    return resultDto.getAnswers().stream()
        .filter(answerDto -> isCorrectAnswer(questions, answerDto))
        .count();

  }

  private Map<String, QuestionDto> collectQuestionsById(Set<QuestionDto> questions) {
    return questions.stream()
        .collect(Collectors.toMap(QuestionDto::getId, question -> question));
  }

  private boolean isCorrectAnswer(Map<String, QuestionDto> questions, AnswerDto answerDto) {
    if (questions.containsKey(answerDto.getQuestionId())) {
      QuestionDto question = questions.get(answerDto.getQuestionId());
      return question.getVariants().stream()
          .filter(variantDto -> variantDto.isCorrected()
              && variantDto.getDescription().equals(answerDto.getAnswer()))
          .count() == 1;
    }
    return false;
  }
}
