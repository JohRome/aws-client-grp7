package com.jromeo.api;

import com.google.gson.Gson;
import com.jromeo.dto.EmailDto;
import com.jromeo.dto.TokenDto;
import com.jromeo.dto.LoginDto;
import com.jromeo.dto.RegisterDto;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AdminApi {

    private static final String TOKEN_TYPE = "Bearer ";
    private String accessToken;
    private String refreshToken;

    public void adminLogin(LoginDto loginDto) throws URISyntaxException, IOException, InterruptedException {
        Gson gson = new Gson();
        String json = gson.toJson(loginDto);


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest post = HttpRequest.newBuilder()
                .uri(new URI("http://school-mangement.eu-north-1.elasticbeanstalk.com:8080/api/v1/auth/authenticate"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(post, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }

        TokenDto tokenDto = gson.fromJson(response.body(), TokenDto.class);

        accessToken = tokenDto.getAccess_token();
        refreshToken = tokenDto.getRefresh_token();
    }

    // Use the same URL as in adminLogin method
    public void userRegister(RegisterDto registerDto) throws URISyntaxException, IOException, InterruptedException {
        Gson gson = new Gson();
        String json = gson.toJson(registerDto);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest post = HttpRequest.newBuilder()
                .uri(new URI("http://school-mangement.eu-north-1.elasticbeanstalk.com:8080/api/v1/auth/userRegister"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(post, HttpResponse.BodyHandlers.ofString());
        TokenDto tokenDto = gson.fromJson(response.body(), TokenDto.class);
        System.out.println(tokenDto);

    }

    // Use the same URL format as in adminLogin method
    public void deleteUserByEmail(LoginDto loginDto, String email) throws URISyntaxException, IOException, InterruptedException {
        adminLogin(loginDto);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest post = HttpRequest.newBuilder()
                .uri(new URI("http://school-mangement.eu-north-1.elasticbeanstalk.com:8080/api/v1/users/delete/" + email))
                .header("Authorization", TOKEN_TYPE + accessToken)
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = client.send(post, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Use the same URL format as in adminLogin method
    public void updateToAdminRole(LoginDto loginDto, EmailDto email) throws URISyntaxException, IOException, InterruptedException {

        adminLogin(loginDto);
        Gson gson = new Gson();
        String json = gson.toJson(email);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest post = HttpRequest.newBuilder()
                .uri(new URI("http://school-mangement.eu-north-1.elasticbeanstalk.com:8080/api/v1/users/promoteToAdmin"))
                .header("Content-Type", "application/json")
                .header("Authorization", TOKEN_TYPE + accessToken)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(post, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
