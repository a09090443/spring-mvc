package com.zipe.config;

import com.zaxxer.hikari.HikariDataSource;
import com.zipe.config.base.BaseDataSourceConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableAspectJAutoProxy
@PropertySource({"classpath:data-source.properties"})
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.zipe",
        entityManagerFactoryRef = "multiEntityManager",
        transactionManagerRef = "multiTransactionManager"
)
public class DataSourceConfig extends BaseDataSourceConfig {

    @Bean(name = "primaryDataSource")
    @Primary
    protected DataSource primaryDataSource() {
        baseHikariConfig().setJdbcUrl(env.getProperty("primary.datasource.url")); //資料來源
        baseHikariConfig().setUsername(env.getProperty("primary.datasource.username")); //使用者名稱
        baseHikariConfig().setPassword(env.getProperty("primary.datasource.password")); //密碼
        baseHikariConfig().setDriverClassName(env.getProperty("primary.datasource.driverClassName"));

        return new HikariDataSource(baseHikariConfig());
    }

    @Bean(name = "secondaryDataSource")
    protected DataSource secondaryDataSource() {
        baseHikariConfig().setJdbcUrl(env.getProperty("secondary.datasource.url")); //資料來源
        baseHikariConfig().setUsername(env.getProperty("secondary.datasource.username")); //使用者名稱
        baseHikariConfig().setPassword(env.getProperty("secondary.datasource.password")); //密碼
        baseHikariConfig().setDriverClassName(env.getProperty("secondary.datasource.driverClassName"));
        baseHikariConfig().setConnectionTestQuery("VALUES 1"); // AS400 語法不能使用 SELECT 1

        return new HikariDataSource(baseHikariConfig());
    }

    @Bean
    public DataSource dataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("primary", primaryDataSource());
        dataSourceMap.put("secondary", secondaryDataSource());

        DataSourceHolder.dataSourceNames.add("primary");
        DataSourceHolder.dataSourceNames.add("secondary");

        DynamicDataSource dataSource = new DynamicDataSource();
        //設定資料來源對映
        dataSource.setTargetDataSources(dataSourceMap);
        //設定預設資料來源，當無法對映到資料來源時會使用預設資料來源
        dataSource.setDefaultTargetDataSource(primaryDataSource());
        dataSource.afterPropertiesSet();
        return dataSource;
    }

    @Bean(name = "multiEntityManager")
    public LocalContainerEntityManagerFactoryBean multiEntityManager() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.zipe.model");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        jpaProperties.put("hibernate.dialect", env.getProperty("spring.jpa.hibernate.dialect"));
        factory.setJpaProperties(jpaProperties);
        return factory;
    }

    @Bean(name = "multiTransactionManager")
    @Qualifier("multiEntityManager")
    public PlatformTransactionManager multiTransactionManager() {
        return new JpaTransactionManager(multiEntityManager().getObject());
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcDaoSupport namedParameterJdbcDaoSupport(@Qualifier("dataSource") DataSource dataSource) {
        NamedParameterJdbcDaoSupport dao = new NamedParameterJdbcDaoSupport();
        dao.setDataSource(dataSource);
        return dao;
    }
}
