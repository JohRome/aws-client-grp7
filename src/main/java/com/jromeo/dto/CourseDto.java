package com.jromeo.dto;

import lombok.Data;

@Data
public class CourseDto {
    private long id;
    private String title;
    private String abbreviation;
    private int modules;
    private double fee;
}
