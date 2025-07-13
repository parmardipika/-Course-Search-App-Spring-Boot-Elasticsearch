package com.undoschool.course_search.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

@Data
@Schema(description = "DTO for searching courses with filters and pagination")
public class SearchRequestDTO {

    @Schema(description = "Search keyword for course title/description", example = "math robotics coding")
    private String q;

    @Schema(description = "Course category", example = "STEM")
    private String category;

    @Schema(description = "Course type", example = "Live")
    private String type;

    @Schema(description = "Minimum age allowed", example = "10")
    private Integer minAge;

    @Schema(description = "Maximum age allowed", example = "15")
    private Integer maxAge;

    @Schema(description = "Minimum price", example = "100.0")
    private Double minPrice;

    @Schema(description = "Maximum price", example = "500.0")
    private Double maxPrice;

//    @Schema(description = "Start date for filtering upcoming sessions", example = "2025-07-20")
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDate startDate;

    @Schema(description = "Sort by: priceAsc, priceDesc, upcoming", example = "priceAsc")
    private String sort;

    @Schema(description = "Page number (starting from 0)", defaultValue = "0")
    private int page = 0;

    @Schema(description = "Number of results per page", defaultValue = "10")
    private int size = 10;
}
