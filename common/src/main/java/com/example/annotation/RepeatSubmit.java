package com.example.annotation;

import java.lang.annotation.*;

/**
 * @author barry.jt.huang
 */
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {
}
