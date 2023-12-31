package com.olegmng.systemTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface Test {
    //добавляем параметр order() со значением 0 по умолчанию
    int order() default 0;

}
