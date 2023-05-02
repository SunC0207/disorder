package com.disorder.authentication.Service;

import com.disorder.authentication.Entity.AuthenticationRequest;
import com.disorder.authentication.Entity.AuthenticationResponse;
import com.disorder.authentication.jwt.Entity.JwtToken;
import com.disorder.authentication.jwt.Entity.TokenType;
import com.disorder.authentication.jwt.Repository.JwtTokenRepository;
import com.disorder.authentication.jwt.Service.JwtService;
import com.disorder.registration.Entity.ConfirmationToken;
import com.disorder.registration.Entity.RegisterRequest;
import com.disorder.registration.Service.ConfirnatiomTokenService;
import com.disorder.registration.Service.EmailValidator;
import com.disorder.user.Entity.Role;
import com.disorder.user.Entity.User;
import com.disorder.user.Repository.UserRepository;
import com.disorder.user.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenRepository jwtTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailValidator emailValidator;
    private final ConfirnatiomTokenService confirnatiomTokenService;
    private final UserService userService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

//        if (!user.isEnabled()) {
//            throw new IllegalStateException("此用戶未經驗證");
//        }

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
    public AuthenticationResponse register(RegisterRequest request) {

        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("不是有效的email");
        }

        boolean userExists = repository.findByEmail(request.getEmail()).isPresent();

        if (userExists) {
            throw new IllegalStateException("此email已被使用");
        }

        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        var saveUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(saveUser, jwtToken);


        String verifyToken = UUID.randomUUID().toString();

        var confirmationToken = ConfirmationToken.builder()
                .token(verifyToken)
                .createTime(LocalDateTime.now())
                .expireTime(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();

        confirnatiomTokenService.saveConfirmationToken(confirmationToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirnatiomTokenService
                .findByToken(token)
                .orElseThrow(() -> new IllegalStateException("無效的驗證"));
        if (confirmationToken.getConfirmTime() != null){
            throw new IllegalStateException("帳號已驗證 請勿重複驗證");
        }
        LocalDateTime expireTime = confirmationToken.getExpireTime();

        if (expireTime.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("驗證信已過期");
        }

        confirnatiomTokenService.setConfirmTime(token);
        userService.enableUser(confirmationToken.getUser().getEmail());

        return "驗證成功";
    }
    private void revokeAllUserTokens(User user) {
        var validUserTokens = jwtTokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(jwtToken -> {
            jwtToken.setExpired(true);
            jwtToken.setRevoked(true);
        });
        jwtTokenRepository.saveAll(validUserTokens);
    }
    private void saveUserToken(User user, String jwtToken){
        var token = JwtToken.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        jwtTokenRepository.save(token);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user,accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }


}
