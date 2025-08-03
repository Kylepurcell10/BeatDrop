package com.kpurcell.BeatDrop.api.auth.controller;

import com.kpurcell.BeatDrop.api.auth.service.AuthService;
import com.kpurcell.BeatDrop.api.auth.service.data.LoginRequest;
import com.kpurcell.BeatDrop.api.users.service.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path="/auth")
public class AuthController
{
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService)
    {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.authenticate(loginRequest.getEmailAddress(), loginRequest.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
