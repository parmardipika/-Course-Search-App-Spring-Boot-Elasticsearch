package com.undoschool.course_search.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.undoschool.course_search.document.CourseDocument;
import com.undoschool.course_search.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseIndexService {

    private final CourseRepository courseRepository;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void loadDataOnStartup() {
        try {
            // Check if data already exists
            if (courseRepository.count() > 0) {
                log.info("ℹ️ Courses already indexed. Skipping initial load.");
                return;
            }

            InputStream inputStream = getClass().getResourceAsStream("/sample-courses.json");
            if (inputStream == null) {
                log.error("❌ Resource '/sample-courses.json' not found. Please check the file path.");
                return;
            }

            List<CourseDocument> courses = objectMapper.readValue(inputStream, new TypeReference<>() {});
            courseRepository.saveAll(courses);

            log.info("✅ Successfully indexed {} courses into Elasticsearch.", courses.size());

        } catch (com.fasterxml.jackson.databind.exc.InvalidFormatException dateEx) {
            log.error("❌ Date format mismatch in 'sample-courses.json'. Ensure 'nextSessionDate' is ISO format (e.g., 2025-07-20T04:30:00+05:30).", dateEx);
        } catch (Exception e) {
            log.error("❌ Failed to load and index sample courses", e);
        }
    }
}
