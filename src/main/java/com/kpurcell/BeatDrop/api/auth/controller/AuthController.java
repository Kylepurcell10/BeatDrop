package com.kpurcell.BeatDrop.api.auth.controller;

import com.kpurcell.BeatDrop.api.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/auth")
public class AuthController
{
    @Autowired
    private AuthService authService;

    @PostMapping(path = "/login")
    public ResponseEntity<String, String>
}
