package com.services.practical.task.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@SpringBootTest
public class ServiceRequestControllerTest {
    @Test
    void contextLoads(ApplicationContext context) {
        assertThat(context).isNotNull();
    }

    @Test
    void mainTest() {
        assertDoesNotThrow(() -> DemoApplication.main(new String[]{}));
    }

}
