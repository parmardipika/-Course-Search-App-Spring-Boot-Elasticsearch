package com.undoschool.course_search.repository;

import com.undoschool.course_search.document.CourseDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends ElasticsearchRepository<CourseDocument, String> {
    List<CourseDocument> findByTitleContainingIgnoreCaseOrInstructorContainingIgnoreCase(String titleKeyword, String instructorKeyword);
}
