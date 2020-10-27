package com.zipe.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zipe.config.base.BaseDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource({"classpath:data-source.properties"})
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.zipe.repository.primary",
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryDataSourceConfig extends BaseDataSourceConfig {
    @Override
    @Bean(name = "primaryDataSource")
    protected DataSource dataSource() {
        baseHikariConfig().setJdbcUrl(env.getProperty("primary.datasource.url")); //資料來源
        baseHikariConfig().setUsername(env.getProperty("primary.datasource.username")); //使用者名稱
        baseHikariConfig().setPassword(env.getProperty("primary.datasource.password")); //密碼
        return new HikariDataSource(baseHikariConfig());
    }

    @Override
    @Bean(name = "primaryTransactionManager")
    @Qualifier("primaryEntityManagerFactory")
    protected PlatformTransactionManager dataSource(EntityManagerFactory primaryEntityManagerFactory) {
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }

    @Override
    @Bean(name = "primaryEntityManagerFactory")
    protected LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.zipe.model.primary");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        jpaProperties.put("hibernate.dialect", env.getProperty("spring.jpa.hibernate.dialect"));
        factory.setJpaProperties(jpaProperties);
        return factory;
    }
}
