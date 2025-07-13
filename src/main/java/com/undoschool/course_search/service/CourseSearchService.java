package com.undoschool.course_search.service;

import com.undoschool.course_search.dto.SearchRequestDTO;
import com.undoschool.course_search.dto.SearchResponseDTO;
import com.undoschool.course_search.document.CourseDocument;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;
//import java.time.LocalDate;
import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseSearchService {

    private final ElasticsearchRestTemplate elasticsearchTemplate;

    public List<SearchResponseDTO> searchCourses(SearchRequestDTO request) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        if (request.getQ() != null && !request.getQ().isEmpty()) {
            MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery(request.getQ())
                    .field("title")
                    .field("description")
                    .fuzziness(Fuzziness.AUTO);
            boolQuery.must(multiMatchQuery);
        }

        // Full-text search on title & description
//        if (request.getStartDate() != null) {
//            String formattedDate = request.getStartDate()
//                    .atTime(0, 0)
//                    .atOffset(ZoneOffset.of("+05:30"))
//                    .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//            boolQuery.filter(QueryBuilders.rangeQuery("startDate").gte(formattedDate));
//        }


        // Filters
        if (request.getCategory() != null)
            boolQuery.filter(QueryBuilders.termQuery("category", request.getCategory()));

        if (request.getType() != null)
            boolQuery.filter(QueryBuilders.termQuery("type", request.getType()));

        if (request.getMinAge() != null && request.getMaxAge() != null) {
            boolQuery.filter(QueryBuilders.rangeQuery("minAge").lte(request.getMaxAge()));
            boolQuery.filter(QueryBuilders.rangeQuery("maxAge").gte(request.getMinAge()));
        } else if (request.getMinAge() != null) {
            // filter courses whose maxAge is >= request minAge
            boolQuery.filter(QueryBuilders.rangeQuery("maxAge").gte(request.getMinAge()));
        } else if (request.getMaxAge() != null) {
            // filter courses whose minAge is <= request maxAge
            boolQuery.filter(QueryBuilders.rangeQuery("minAge").lte(request.getMaxAge()));
        }


        if (request.getMinPrice() != null || request.getMaxPrice() != null) {
            RangeQueryBuilder priceRange = QueryBuilders.rangeQuery("price");
            if (request.getMinPrice() != null) priceRange.gte(request.getMinPrice());
            if (request.getMaxPrice() != null) priceRange.lte(request.getMaxPrice());
            boolQuery.filter(priceRange);
        }

//        LocalDate date = LocalDate.now();
//        ZoneId zone = ZoneId.systemDefault();
//
//        ZonedDateTime zdt = date.atStartOfDay(zone);
        // Sorting
        Sort sort = Sort.by("price").ascending(); // Default sort by price ascending

        if ("priceAsc".equals(request.getSort())) {
            sort = Sort.by("price").ascending();
        } else if ("priceDesc".equals(request.getSort())) {
            sort = Sort.by("price").descending();
        } else if ("titleAsc".equals(request.getSort())) {
            sort = Sort.by("title").ascending();
        } else if ("titleDesc".equals(request.getSort())) {
            sort = Sort.by("title").descending();
        }

        // Pagination
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize(), sort);

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .withPageable(pageRequest)
                .build();

        SearchHits<CourseDocument> hits = elasticsearchTemplate.search(query, CourseDocument.class);

        return hits.getSearchHits().stream()
                .map(hit -> {
                    CourseDocument course = hit.getContent();
                    return new SearchResponseDTO(
                            course.getId(),
                            course.getTitle(),
                            course.getCategory(),
                            course.getPrice()
//                            course.getStartDate()

                    );
                })
                .collect(Collectors.toList());

    }
}
