package com.zipe.service.test;

import com.zipe.model.primary.InvoM020;
import com.zipe.repository.primary.InvoM020Repository;
import com.zipe.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(locations = "file:web/WEB-INF/spring-servlet.xml")
public class HelloWorldServiceTest {
    @Autowired
    private HelloWorldService helloWorldService;

    @Test
    public void primaryDataTest() {
        helloWorldService.primaryData();
    }

    @Test
    public void secondaryDataTest() {
        helloWorldService.secondaryData();
    }
}
