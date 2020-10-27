package com.zipe.config.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

public abstract class BaseDataSourceConfig {
    @Autowired
    protected Environment env;

    protected abstract DataSource dataSource();

    protected abstract PlatformTransactionManager dataSource(EntityManagerFactory entityManagerFactory);

    protected abstract LocalContainerEntityManagerFactoryBean entityManagerFactory();

    protected Properties hibernateSetting(){
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        jpaProperties.put("hibernate.dialect", env.getProperty("spring.jpa.hibernate.dialect"));
        return jpaProperties;
    }
}
