package com.zipe.config;

import com.zipe.config.base.BaseDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.zipe.repository.secondary",
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        transactionManagerRef = "secondaryTransactionManager"
)
public class SecondaryDataSourceConfig extends BaseDataSourceConfig {
//    @Autowired
//    private Environment env;
//
//    @Bean(name = "secondaryDataSource")
//    public DataSource secondaryDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("secondary.datasource.driverClassName"));
//        dataSource.setUrl(env.getProperty("secondary.datasource.url"));
//        dataSource.setUsername(env.getProperty("secondary.datasource.username"));
//        dataSource.setPassword(env.getProperty("secondary.datasource.password"));
//
//        return dataSource;
//    }
//
//    @Bean(name = "secondaryTransactionManager")
//    public PlatformTransactionManager secondaryTransactionManager() {
//        EntityManagerFactory factory = secondaryEntityManagerFactory().getObject();
//        return new JpaTransactionManager(factory);
//    }
//
//    @Bean(name = "secondaryEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setDataSource(secondaryDataSource());
//        factory.setPackagesToScan("com.zipe.model.secondary");
//        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//
//        Properties jpaProperties = new Properties();
//        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
//        jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
//        jpaProperties.put("hibernate.dialect", env.getProperty("spring.jpa.hibernate.dialect"));
//        factory.setJpaProperties(jpaProperties);
//
//        return factory;
//    }

    @Override
    @Bean(name = "secondaryDataSource")
    protected DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("secondary.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("secondary.datasource.url"));
        dataSource.setUsername(env.getProperty("secondary.datasource.username"));
        dataSource.setPassword(env.getProperty("secondary.datasource.password"));

        return dataSource;
    }

    @Override
    @Bean(name = "secondaryTransactionManager")
    @Qualifier("secondaryEntityManagerFactory")
    protected PlatformTransactionManager dataSource(EntityManagerFactory secondaryEntityManagerFactory) {
        return new JpaTransactionManager(secondaryEntityManagerFactory);
    }

    @Override
    @Bean(name = "secondaryEntityManagerFactory")
    protected LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.zipe.model.secondary");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        jpaProperties.put("hibernate.dialect", env.getProperty("spring.jpa.hibernate.dialect"));
        factory.setJpaProperties(jpaProperties);

        return factory;
    }
}
