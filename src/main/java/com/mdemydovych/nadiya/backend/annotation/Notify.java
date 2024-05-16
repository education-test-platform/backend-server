package com.mdemydovych.nadiya.backend.annotation;

import com.mdemydovych.nadiya.backend.model.Event;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Notify {

  Event value();
}
