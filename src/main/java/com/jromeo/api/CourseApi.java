package com.jromeo.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jromeo.dto.CourseDto;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class  CourseApi {
    private final String baseUrl = "http://school-mangement.eu-north-1.elasticbeanstalk.com:8080/api/v1/courses";

    public void addCourse(CourseDto courseDto) throws IOException, InterruptedException, URISyntaxException {
        String addCourseUrl = baseUrl + "/save";

        Gson gson = new Gson();
        String jsonCourse = gson.toJson(courseDto);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest addCourseRequest = HttpRequest.newBuilder()
                .uri(new URI(addCourseUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonCourse))
                .build();

        HttpResponse<String> response = client.send(addCourseRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("New course added");
            CourseDto addedCourse = gson.fromJson(response.body(), CourseDto.class);
            System.out.println("New course: " + addedCourse);
        } else {
            System.out.println("Error adding course. Status: " + response.statusCode());
        }
    }

    public void getOneCourse(long courseId) throws URISyntaxException, IOException, InterruptedException {
        String getOneCourseUrl = baseUrl + "/getCourse/" + courseId;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(getOneCourseUrl))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();

        if (statusCode == 200) {
            Gson gson = new Gson();
            CourseDto course = gson.fromJson(response.body(), CourseDto.class);
            System.out.println("Course: " + course);
        } else {
            System.out.println("Error fetching course. Status: " + statusCode);
        }
    }

    public List<CourseDto> getAllCourses() throws URISyntaxException, IOException, InterruptedException {
        String getAllCoursesUrl = baseUrl + "/getCourses";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(getAllCoursesUrl))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();

        if (statusCode == 200) {
            Gson gson = new Gson();
            Type courseListType = new TypeToken<ArrayList<CourseDto>>() {}.getType();
            List<CourseDto> courses = gson.fromJson(response.body(), courseListType);
            System.out.println("Courses: ");
            for (CourseDto course : courses) {
                System.out.println(course);
            }
            return courses;
        } else {
            System.out.println("Error fetching courses. Status: " + statusCode);
        }
        return new ArrayList<>();
    }

    public void updateCourse(CourseDto updatedCourse) throws IOException, InterruptedException, URISyntaxException {
        String updateCourseUrl = baseUrl + "/update/" + updatedCourse.getId();

        Gson gson = new Gson();
        String jsonUpdatedCourse = gson.toJson(updatedCourse);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest updateCourseRequest = HttpRequest.newBuilder()
                .uri(new URI(updateCourseUrl))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonUpdatedCourse))
                .build();

        HttpResponse<String> response = client.send(updateCourseRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("Course updated!");
        } else {
            System.out.println("Error updating course. Status: " + response.statusCode());
        }
    }

    public void deleteCourse(long courseId) throws URISyntaxException, IOException, InterruptedException {
        String deleteCourseUrl = baseUrl + "/delete/" + courseId;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest deleteCourseRequest = HttpRequest.newBuilder()
                .uri(new URI(deleteCourseUrl))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(deleteCourseRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("Course deleted");
        } else {
            System.out.println("Error deleting course. Status: " + response.statusCode());
        }
    }
}
