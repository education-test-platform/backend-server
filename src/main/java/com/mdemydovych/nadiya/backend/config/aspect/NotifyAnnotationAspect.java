package com.mdemydovych.nadiya.backend.config.aspect;

import com.mdemydovych.nadiya.backend.annotation.Notify;
import com.mdemydovych.nadiya.backend.broker.BrokerPublisherService;
import com.mdemydovych.nadiya.backend.broker.handler.TopicHandlerService;
import com.mdemydovych.nadiya.backend.model.Event;
import java.lang.annotation.Annotation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class NotifyAnnotationAspect {

  private final BrokerPublisherService publisherService;

  private final TopicHandlerService topicHandlerService;

  @SneakyThrows
  @Around("@annotation(com.mdemydovych.nadiya.backend.annotation.Notify)")
  public Object notify(ProceedingJoinPoint point) throws BeansException {
    Event event = extractEventType(point);
    Object retVal = point.proceed(point.getArgs());
    processEvent(event, point.getArgs());
    return retVal;
  }

  private Event extractEventType(ProceedingJoinPoint point) {
    MethodSignature methodSignature = (MethodSignature) point.getSignature();
    Annotation[] methodAnnotations = methodSignature.getMethod().getAnnotations();
    for (Annotation annotation : methodAnnotations) {
      if (annotation instanceof Notify event) {
        return event.value();
      }
    }
    throw new NullPointerException("Notify annotation not found");
  }

  private void processEvent(Event event, Object[] args) {
    List<String> topics = topicHandlerService.handleTopics(args[0], event);
    publisherService.sendMessage(event.name(), topics);
  }
}
