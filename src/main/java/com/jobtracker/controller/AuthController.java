package com.jobtracker.controller;
import com.jobtracker.dto.LoginRequest;
import com.jobtracker.dto.LoginResponse;
import com.jobtracker.dto.ApiResponse;
import com.jobtracker.dto.RegisterRequest;
import com.jobtracker.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ApiResponse register(
            @RequestBody RegisterRequest request) {

        String message = authService.register(request);

        return new ApiResponse(message);
    }

    @PostMapping("/login")
public LoginResponse login(
        @RequestBody LoginRequest request) {

    return authService.login(request);
}
}