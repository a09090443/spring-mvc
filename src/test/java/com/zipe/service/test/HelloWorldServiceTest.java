package com.zipe.service.test;

import com.zipe.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(locations = "file:web/WEB-INF/spring-servlet.xml")
public class HelloWorldServiceTest {
    @Autowired
    private HelloWorldService helloWorldService;

    @Test
    public void test() {
        helloWorldService.test();
    }
}
