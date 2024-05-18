package com.wertyxa.springauthjwttemplate.core.web.controllers;

import com.wertyxa.springauthjwttemplate.core.service.AuthenticationService;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.AuthResponse;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.LoginRequest;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.SignupRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/auth/v1")
public class AuthControllerV1 {
    private final AuthenticationService authenticationService;

    public AuthControllerV1(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody SignupRequest signupRequest){
        return authenticationService.register(signupRequest);
    }
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return authenticationService.login(loginRequest);
    }

}
