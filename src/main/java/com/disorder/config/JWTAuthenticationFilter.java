package com.disorder.config;

import com.disorder.token.Repository.JwtTokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final JwtTokenRepository jwtTokenRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        if (request.getServletPath().contains("/api/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization"); // Authorization 是 Http Authentication(Http認證) 的方案 有許多種格式
        final String JWT;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) { // 這裡使用OAuth2 的 Bearer格式 Bearer沒有硬性的Algorithm(演算法)規定 通常JWT都會使用Bearer作為前綴
            filterChain.doFilter(request, response);
            return;
        }

        JWT = authHeader.substring(7); // 我們要取得JWT 所以去除Bearer和空格後開始的索引值是7

        userEmail = jwtService.extractUsername(JWT);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) { // 如果有userEmail 而且未經驗證的話
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail); // 從DB取得user資料
            var isTokenValid = jwtTokenRepository.findByToken(JWT)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            if (jwtService.isTokenValid(JWT, userDetails) && isTokenValid) { // 確認是不是有效的token及user
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken( // new一個認證token
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request) // 用request 的details 來加強token
                );
                SecurityContextHolder.getContext().setAuthentication(authToken); // 最後 更新認證token
            }
        }
        filterChain.doFilter(request, response); // 若有其他濾器 繼續過濾
    }
}
