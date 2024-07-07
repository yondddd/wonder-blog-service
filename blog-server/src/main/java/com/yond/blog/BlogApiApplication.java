package com.yond.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BlogApiApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BlogApiApplication.class, args);
    }

}
