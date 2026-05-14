package com.jobtracker.service;
import com.jobtracker.dto.LoginRequest;
import com.jobtracker.dto.LoginResponse;
import com.jobtracker.security.JwtUtil;
import com.jobtracker.dto.RegisterRequest;
import com.jobtracker.entity.User;
import com.jobtracker.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(request.getRole());

        userRepository.save(user);

        return "User registered successfully";
    }

    public LoginResponse login(LoginRequest request) {

    User user = userRepository.findByUsername(
                    request.getUsername())
            .orElseThrow(() ->
                    new RuntimeException("Invalid username"));

    boolean matches = passwordEncoder.matches(
            request.getPassword(),
            user.getPassword()
    );

    if (!matches) {
        throw new RuntimeException("Invalid password");
    }

    String token = JwtUtil.generateToken(user.getUsername());

    return new LoginResponse(token);
}
}