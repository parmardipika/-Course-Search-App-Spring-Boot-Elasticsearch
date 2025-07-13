package com.undoschool.course_search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CourseSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseSearchApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // for LocalDateTime
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // optional
        return mapper;
    }

}
