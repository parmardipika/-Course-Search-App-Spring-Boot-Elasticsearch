package com.undoschool.course_search.controller;

import com.undoschool.course_search.dto.SearchRequestDTO;
import com.undoschool.course_search.dto.SearchResponseDTO;
import com.undoschool.course_search.service.CourseSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@Tag(name = "Course Search API", description = "Search and filter available courses")
public class CourseSearchController {

    private final CourseSearchService courseSearchService;

    @PostMapping("/search")
    @Operation(summary = "Search courses with filters, sorting, and pagination")
    public ResponseEntity<List<SearchResponseDTO>> searchCourses(@RequestBody SearchRequestDTO request) {
        List<SearchResponseDTO> results = courseSearchService.searchCourses(request);
        return ResponseEntity.ok(results);
    }
}
