package com.wp.project2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

    // 프로그램을 실행하면 제일 처음 실행되는 부분
    public static void main(String[] args) throws Exception {
        // Spring Boot에게 명령하여, WebApplication.java를 호출 -> SpringApplicatonBuilder 호출
        SpringApplication.run(WebApplication.class, args);
    }
}
