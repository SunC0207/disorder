package com.disorder.demo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo-controller")
@RequiredArgsConstructor
public class DemoController {
    private final DemoService service;
    @GetMapping
    public ResponseEntity<String> sayHello(HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok(service.hello(request, response));
    }
}
