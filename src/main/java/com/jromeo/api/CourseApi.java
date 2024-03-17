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

    public void addCourse(CourseDto courseDto) throws IOException, InterruptedException, URISyntaxException {
        String addCourseUri = "http://localhost:8080/course/save";

        var course = new CourseDto();
        course.setTitle(courseDto.getTitle());
        course.setAbbreviation(courseDto.getAbbreviation());
        course.setModules(courseDto.getModules());
        course.setFee(courseDto.getFee());

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

    public void getOneCourse(CourseDto courseDto) throws URISyntaxException, IOException, InterruptedException {
        long id = courseDto.getId();
        String getOneCourseUrl = "http://localhost:8080/course/getCourse/" + id;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(getOneCourseUrl))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();

        if (statusCode == 200) {
            Gson gson = new Gson();
            Type courseType = new TypeToken<CourseDto>() {
            }.getType();
            CourseDto course = gson.fromJson(response.body(), courseType);
            System.out.println(course.toString());
        } else {
            System.out.println("Error fetching course");
        }
    }

    public List<CourseDto> getAllCourses() throws URISyntaxException, IOException, InterruptedException {
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
                return courses;
            } //else {
                System.out.println("Error fetching courses. Status " + response.statusCode());
            //}
        }
        return null;
    }

    public void updateCourse(CourseDto courseDto) throws IOException, InterruptedException, URISyntaxException {
        long id = courseDto.getId();
        String title = scanner.stringPut("Enter updated course title: ");
        String abbreviation = scanner.stringPut("Enter updated course abbreviation: ");
        int modules = scanner.intPut("Enter updated amount of modules: ");
        double fee = scanner.doublePut("Enter updated fee: ");

        var updatedCourse = new CourseDto();
        updatedCourse.setTitle(title);
        updatedCourse.setAbbreviation(abbreviation);
        updatedCourse.setModules(modules);
        updatedCourse.setFee(fee);

        Gson gson = new Gson();
        String jsonUpdatedCourse = gson.toJson(updatedCourse);

        String updatedCourseUri = "http://localhost:8080/course/update/" + id;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest updateCourseRequest = HttpRequest.newBuilder()
                .uri(new URI(updatedCourseUri))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonUpdatedCourse))
                .build();

        HttpResponse<String> response = client.send(updateCourseRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("Course updated!");
        } else {
            System.out.println("Error updating course");
        }
    }

    public void deleteCourse(CourseDto courseDto) throws URISyntaxException, IOException, InterruptedException {
        long id = courseDto.getId();
        List<CourseDto> allCourses = getAllCourses();

        boolean courseExists = allCourses.stream().anyMatch(course -> course.getId() == id);

        if(!courseExists) {
            System.out.println("Course does not exist");
            System.out.flush();
            return;
        }

        String deleteCourseUri = "http://localhost:8080/course/delete/" + id;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest deleteCourseRequest = HttpRequest.newBuilder()
                .uri(new URI(deleteCourseUri))
                .header("Content-type", "application/json")
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = client.send(deleteCourseRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Course deleted");
            } else {
                System.out.println("Error deleting course");
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
