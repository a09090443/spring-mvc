package com.zipe.config.base;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    @Bean("baseHikariConfig")
    protected HikariConfig baseHikariConfig() {
        HikariConfig config = new HikariConfig();
        config.addDataSourceProperty("cachePrepStmts", "true"); //是否自定義配置，為true時下面兩個引數才生效
        config.addDataSourceProperty("prepStmtCacheSize", "250"); //連線池大小預設25，官方推薦250-500
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048"); //單條語句最大長度預設256，官方推薦2048
        config.addDataSourceProperty("useServerPrepStmts", "true"); //新版本MySQL支援伺服器端準備，開啟能夠得到顯著效能提升
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("useLocalTransactionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");
        config.setMinimumIdle(5);
        config.setMaximumPoolSize(20);
        config.setIdleTimeout(30000);
        config.setPoolName("Hikari");
        config.setMaxLifetime(2000000);
        config.setConnectionTimeout(30000);
        config.setAutoCommit(true);
        config.setConnectionTestQuery("SELECT 1");
        return config;
    }

    protected Properties hibernateSetting() {
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        jpaProperties.put("hibernate.dialect", env.getProperty("spring.jpa.hibernate.dialect"));
        return jpaProperties;
    }
}
