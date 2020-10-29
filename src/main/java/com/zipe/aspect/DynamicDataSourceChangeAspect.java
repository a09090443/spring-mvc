package com.zipe.aspect;

import com.zipe.annotation.TargetDataSource;
import com.zipe.config.DataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(-1)// 保證該AOP在@Transactional之前執行
public class DynamicDataSourceChangeAspect {
    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        String dsName = targetDataSource.value();
        if (!DataSourceHolder.containsDataSource(dsName)) {
            log.error("資料來源[{}]不存在，使用預設資料來源 > {}" + targetDataSource.value() + joinPoint.getSignature());
        } else {
            DataSourceHolder.setDataSourceName(targetDataSource.value()); //設定到動態資料來源上下文中
        }
    }

    @After("@annotation(targetDataSource)")
    public void restoreDataSource(JoinPoint point, TargetDataSource targetDataSource) {
        //方法執行完畢之後，銷燬當前資料來源資訊，進行垃圾回收。
        DataSourceHolder.clearDataSourceName();
    }
}
