package com.jromeo.dto;

import lombok.Data;

import java.util.Set;

@Data
public class StudentDto {
    private long id;
    private String name;
    private int age;
    private String dept;
    private Set<CourseDto> courses;
}
