package com.jromeo.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jromeo.dto.CourseDto;
import com.jromeo.utils.InputScanner;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class CourseApi {

    InputScanner scanner = new InputScanner();

    public void addCourse(String courseName, String abbreviation, int modules, double fee) throws IOException, InterruptedException, URISyntaxException {
        String addCourseUri = "http://localhost:8080/course/save";

        var course = new CourseDto();
        course.setTitle(courseName);
        course.setAbbreviation(abbreviation);
        course.setModules(modules);
        course.setFee(fee);

        Gson gson = new Gson();
        String jsonCourse = gson.toJson(course);


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest addCourseRequest = HttpRequest.newBuilder()
                .uri(new URI(addCourseUri))
                .header("Content-Type", "application/json")
                // .header("Authorization", "Bearer " + authToken)
                .POST(HttpRequest.BodyPublishers.ofString(jsonCourse))
                .build();

        HttpResponse<String> response = client.send(addCourseRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("New course added");

            String responseBody = response.body();
            var convertedBody = gson.fromJson(responseBody, CourseDto.class);

            System.out.println("New course: " + convertedBody);

        } else {
            System.out.println("Error adding course. Status: " + response.statusCode());
        }
    }

    public void getOneCourse() {

    }

    public void getAllCourses() throws URISyntaxException, IOException, InterruptedException {
        String coursesUri = "http://localhost:8080/course/getCourses";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(coursesUri))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();

        if (statusCode == 200) {
            Gson gson = new Gson();
            Type courseType = new TypeToken<ArrayList<CourseDto>>(){
            }.getType();
            List<CourseDto> courses = gson.fromJson(response.body(), courseType);
            System.out.println("Courses: ");
            for (CourseDto course : courses) {
                System.out.println(course.toString());
            } //else {
                System.out.println("Error fetching courses. Status " + response.statusCode());
            //}
        }
    }

    public void updateCourse() {

    }

    public void deleteCourse() {

    }
}
