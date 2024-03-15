package com.jromeo.api;

import com.jromeo.dto.EmailDto;
import com.jromeo.dto.LoginDto;
import com.jromeo.dto.RegisterDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class AdminApiTest {

    @Mock
    private AdminApi adminApi;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldLoginAdminSuccessfully() throws URISyntaxException, IOException, InterruptedException {
        LoginDto loginDto = new LoginDto();
        doNothing().when(adminApi).adminLogin(loginDto);
        assertDoesNotThrow(() -> adminApi.adminLogin(loginDto));
        verify(adminApi, times(1)).adminLogin(loginDto);
    }

    @Test
    void shouldRegisterUserSuccessfully() throws URISyntaxException, IOException, InterruptedException {
        RegisterDto registerDto = new RegisterDto();
        doNothing().when(adminApi).userRegister(registerDto);
        assertDoesNotThrow(() -> adminApi.userRegister(registerDto));
        verify(adminApi, times(1)).userRegister(registerDto);
    }

    @Test
    void shouldUpdateUserByEmailSuccessfully() throws URISyntaxException, IOException, InterruptedException {
        LoginDto loginDto = new LoginDto();
        RegisterDto updatedUser = new RegisterDto();
        doNothing().when(adminApi).updateUserByEmail(loginDto, "test@test.com", updatedUser);
        assertDoesNotThrow(() -> adminApi.updateUserByEmail(loginDto, "test@test.com", updatedUser));
        verify(adminApi, times(1)).updateUserByEmail(loginDto, "test@test.com", updatedUser);
    }

    @Test
    void shouldDeleteUserByEmailSuccessfully() throws URISyntaxException, IOException, InterruptedException {
        LoginDto loginDto = new LoginDto();
        doNothing().when(adminApi).deleteUserByEmail(loginDto, "test@test.com");
        assertDoesNotThrow(() -> adminApi.deleteUserByEmail(loginDto, "test@test.com"));
        verify(adminApi, times(1)).deleteUserByEmail(loginDto, "test@test.com");
    }

    @Test
    void shouldPromoteUserToAdminSuccessfully() throws URISyntaxException, IOException, InterruptedException {
        LoginDto loginDto = new LoginDto();
        EmailDto emailDto = new EmailDto();
        doNothing().when(adminApi).updateToAdminRole(loginDto, emailDto);
        assertDoesNotThrow(() -> adminApi.updateToAdminRole(loginDto, emailDto));
        verify(adminApi, times(1)).updateToAdminRole(loginDto, emailDto);
    }
}