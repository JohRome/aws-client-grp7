package com.jromeo.api;

import com.jromeo.dto.CourseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseApiTest {
    @Mock
    private CourseApi courseApi;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCourse() throws IOException, URISyntaxException, InterruptedException {
        CourseDto courseDto = new CourseDto();
        doNothing().when(courseApi).addCourse(courseDto);
        assertDoesNotThrow(() -> courseApi.addCourse(courseDto));
        verify(courseApi, times(1)).addCourse(courseDto);
    }

    @Test
    void testGetOneCourse() throws URISyntaxException, IOException, InterruptedException {
        CourseDto courseDto = new CourseDto();
        doNothing().when(courseApi).getOneCourse(courseDto);
        assertDoesNotThrow(() -> courseApi.getOneCourse(courseDto));
        verify(courseApi, times(1)).getOneCourse(courseDto);
    }

    @Test
    void testGetAllCourses() throws URISyntaxException, IOException, InterruptedException {
        List<CourseDto> mockedCourses = new ArrayList<>();
        when(courseApi.getAllCourses()).thenReturn(mockedCourses);

        List<CourseDto> returnedCourses = courseApi.getAllCourses();
        verify(courseApi, times(1)).getAllCourses();

        assertNotNull(returnedCourses);
        assertTrue(returnedCourses.isEmpty());
    }

    @Test
    void testUpdateCourse() throws IOException, URISyntaxException, InterruptedException {
        CourseDto courseDto = new CourseDto();
        doNothing().when(courseApi).updateCourse(courseDto);
        assertDoesNotThrow(() -> courseApi.updateCourse(courseDto));
        verify(courseApi, times(1)).updateCourse(courseDto);
    }

    @Test
    void deleteCourse() throws IOException, URISyntaxException, InterruptedException {
        CourseDto courseDto = new CourseDto();
        doNothing().when(courseApi).deleteCourse(courseDto);
        assertDoesNotThrow(() -> courseApi.deleteCourse(courseDto));
        verify(courseApi, times(1)).deleteCourse(courseDto);
    }
}