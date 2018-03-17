package com.mijack.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan("com.mijack.course.**")
//@MapperScan("com.mijack.course.dao.**")
@ImportResource(locations = {"**.xml"})
public class MoocApplication {

    public static void main(String[] args) {

        SpringApplication.run(MoocApplication.class, args);

    }
}
