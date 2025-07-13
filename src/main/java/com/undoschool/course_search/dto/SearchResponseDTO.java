package com.undoschool.course_search.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@Schema(description = "Course Search Response")
public class SearchResponseDTO {

    @Schema(example = "course123")
    private String id;

    @Schema(example = "Intro to Robotics")
    private String title;

    @Schema(example = "STEM")
    private String category;

    @Schema(example = "150.0")
    private Double price;

//    @Schema(example = "2025-07-20T04:30:00+05:30")
//    private ZonedDateTime startDate;
}
