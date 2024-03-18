package com.jromeo.api;

import com.jromeo.dto.CourseDto;
import com.jromeo.dto.StudentDto;
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

class StudentApiTest {
    @Mock
    private StudentApi studentApi;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStudent() throws InterruptedException, IOException {
        StudentDto studentDto = new StudentDto();
        doNothing().when(studentApi).createStudent(studentDto);
        assertDoesNotThrow(() -> studentApi.createStudent(studentDto));
        verify(studentApi, times(1)).createStudent(studentDto);
    }

    @Test
    void testGetOneStudent() throws URISyntaxException, IOException, InterruptedException {
        StudentDto studentDto = new StudentDto();
        doNothing().when(studentApi).getOneStudent(studentDto);
        assertDoesNotThrow(() -> studentApi.getOneStudent(studentDto));
        verify(studentApi, times(1)).getOneStudent(studentDto);
    }

    @Test
    void testGetAllStudents() throws URISyntaxException, IOException, InterruptedException {
        List<StudentDto> mockedStudents = new ArrayList<>();

        when(studentApi.getAllStudents()).thenReturn(mockedStudents);

        List<StudentDto> returnedStudents = studentApi.getAllStudents();

        verify(studentApi, times(1)).getAllStudents();

        assertNotNull(returnedStudents);
        assertTrue(returnedStudents.isEmpty());
    }

    @Test
    void testUpdateStudent() throws IOException, URISyntaxException, InterruptedException {
        StudentDto studentDto = new StudentDto();
        doNothing().when(studentApi).updateStudent(studentDto);
        assertDoesNotThrow(() -> studentApi.updateStudent(studentDto));
        verify(studentApi, times(1)).updateStudent(studentDto);
    }

    @Test
    void testDeleteStudent() throws URISyntaxException, IOException, InterruptedException {
    StudentDto studentDto = new StudentDto();
    doNothing().when(studentApi).deleteStudent(studentDto);
    assertDoesNotThrow(() -> studentApi.deleteStudent(studentDto));
    verify(studentApi, times(1)).deleteStudent(studentDto);
    }

    @Test
    void testAssignCourseToStudent() throws URISyntaxException, IOException, InterruptedException {
        StudentDto studentDto = new StudentDto();
        CourseDto courseDto = new CourseDto();
        doNothing().when(studentApi).assignCourseToStudent(studentDto, courseDto);
        assertDoesNotThrow(() -> studentApi.assignCourseToStudent(studentDto, courseDto));
        verify(studentApi, times(1)).assignCourseToStudent(studentDto, courseDto);
    }
}