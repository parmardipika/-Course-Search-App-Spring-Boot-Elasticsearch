package com.undoschool.course_search.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.DateFormat;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "courses")
public class CourseDocument {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Keyword)
    private String type; // ONE_TIME, COURSE, CLUB

    @Field(type = FieldType.Keyword)
    private String gradeRange;

    @Field(type = FieldType.Integer)
    private Integer minAge;

    @Field(type = FieldType.Integer)
    private Integer maxAge;

    @Field(type = FieldType.Double)
    private Double price;

//    @Field(type = FieldType.Date, format = DateFormat.date)
//    private ZonedDateTime startDate;


    @Field(type = FieldType.Text)
    private String instructor;


}
