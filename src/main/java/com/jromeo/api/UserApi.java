package com.jromeo.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jromeo.dto.UserDto;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class UserApi {
    // Author Jonas Torjman
    private final String baseUrl = "http://localhost:8080/api/v1";

    public void getAllUsers() throws URISyntaxException, IOException, InterruptedException {
        String getUsersUrl = baseUrl + "/users/getUsers";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(getUsersUrl))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();

        if (statusCode == 200) {
            Gson gson = new Gson();
            Type userType = new TypeToken<ArrayList<UserDto>>() {}.getType();
            List<UserDto> users = gson.fromJson(response.body(), userType);
            System.out.println("Users:");
            for (UserDto user : users) {
                System.out.println(user.toString());
            }
        } else {
            System.out.println("Error fetching users. Status: " + statusCode);
        }
    }

    public void promoteUserToAdmin(String email) throws URISyntaxException, IOException, InterruptedException {
        String promoteUrl = baseUrl + "/users/promoteToAdmin";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest promoteRequest = HttpRequest.newBuilder()
                .uri(new URI(promoteUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{ \"email\": \"" + email + "\" }"))
                .build();

        HttpResponse<String> response = client.send(promoteRequest, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();

        if (statusCode == 200) {
            System.out.println("User promoted to admin successfully.");
        } else {
            System.out.println("Error promoting user to admin. Status: " + statusCode);
        }
    }
    public void updateUser(String email, String newPassword) throws URISyntaxException, IOException, InterruptedException {
        String updateUserUrl = baseUrl + "/users/update";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest updateRequest = HttpRequest.newBuilder()
                .uri(new URI(updateUserUrl))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"email\": \"" + email + "\", \"password\": \"" + newPassword + "\" }"))
                .build();

        HttpResponse<String> response = client.send(updateRequest, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();

        if (statusCode == 200) {
            System.out.println("User updated successfully.");
        } else {
            System.out.println("Error updating user. Status: " + statusCode);
        }
    }

    public void deleteUser(String email) throws URISyntaxException, IOException, InterruptedException {
        String deleteUserUrl = baseUrl + "/users/delete";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(new URI(deleteUserUrl))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();

        if (statusCode == 200) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Error deleting user. Status: " + statusCode);
        }
    }

}

