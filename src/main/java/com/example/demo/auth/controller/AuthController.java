package com.example.demo.auth.controller;

import com.example.demo.auth.dto.LoginRequest;
import com.example.demo.auth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping( "/api/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Map<String, Object> result = authService.login(loginRequest);
        Map<String, Object> data = new HashMap<>();
        if (result.get("message").equals("Success")) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            if (!authentication.isAuthenticated()) {
                return new ResponseEntity<>("Tenemos problemas, reintente mas tarde...", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            data.put("message", result.get("token"));
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        data.put("message", result.get("message"));
        return new ResponseEntity<>(data, HttpStatus.UNAUTHORIZED);
    }
}
