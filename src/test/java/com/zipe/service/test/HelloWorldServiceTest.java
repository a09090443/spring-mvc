package com.zipe.service.test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zipe.model.primary.InvoM020;
import com.zipe.primary.service.PrimaryService;
import com.zipe.repository.primary.InvoM020Repository;
import com.zipe.secondary.service.SecondaryService;
import com.zipe.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(locations = "file:web/WEB-INF/spring-servlet.xml")
public class HelloWorldServiceTest {
    @Autowired
    private HelloWorldService helloWorldService;
    @Autowired
    private PrimaryService primaryService;
    @Autowired
    private SecondaryService secondaryService;

    @Test
    public void primaryDataTest() {
        primaryService.queryTest();
    }

    @Test
    public void secondaryDataTest() {
        secondaryService.queryTest();
    }
}
