package com.zipe.aspect;

import com.zipe.config.DataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(-1)// 保證該AOP在@Transactional之前執行
public class DataSourceServiceAspect {

    @Pointcut("execution(* com.zipe.primary.service..*.*(..))")
    public void primaryServicePointcut() {
    }

    @Pointcut("execution(* com.zipe.secondary.service..*.*(..))")
    public void secondaryServicePointcut() {
    }

    @Around("primaryServicePointcut()")
    public Object PrimaryAround(ProceedingJoinPoint pjp) throws Throwable {
        return switchDataSource("primary", pjp);
    }

    @Around("secondaryServicePointcut()")
    public Object SecondaryAround(ProceedingJoinPoint pjp) throws Throwable {
        return switchDataSource("secondary", pjp);
    }

    private Object switchDataSource(String dataSourceName, ProceedingJoinPoint pjp) throws Throwable {
        DataSourceHolder.clearDataSourceName();
        if (!DataSourceHolder.containsDataSource(dataSourceName)) {
            log.error("資料來源[{}]不存在，使用預設資料來源 > {}" + dataSourceName + pjp.getSignature());
        } else {
            DataSourceHolder.setDataSourceName(dataSourceName); //設定到動態資料來源上下文中
        }
        return pjp.proceed();
    }
}
