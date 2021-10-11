package com.test.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ControllerTest {


    @Autowired
    private PersonController controller;

    @Test
    public void contextLoads() throws Exception{
        assertThat(controller).isNotNull();
    }
}
