package com.zipe.annotation;

import java.lang.annotation.*;

/**
 * 在方法上使用，用於指定使用哪個資料來源
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String value();
}
