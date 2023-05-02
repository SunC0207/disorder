package com.disorder.demo;

import com.disorder.config.JwtService;
import com.disorder.user.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoService {
    private final JwtService jwtService;
    private final UserRepository repository;
    public String hello(HttpServletRequest request, HttpServletResponse response){
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        var user = this.repository.findByEmail(userEmail)
                .orElseThrow();
        return "你好阿"+user.getName();
    }
}
