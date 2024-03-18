package com.jromeo.dto;

public class UserDto {
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    @Override
    public String toString() {
        return "UserDto{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}