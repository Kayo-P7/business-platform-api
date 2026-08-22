package com.Vy.telegram_bot.controller;

import com.Vy.telegram_bot.dto.Request.LoginRequest;
import com.Vy.telegram_bot.model.User;
import com.Vy.telegram_bot.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<Authentication> login(@RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(authenticationService.auth(loginRequest));

    }
}
