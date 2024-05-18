package com.wertyxa.springauthjwttemplate.core.service;


import com.wertyxa.springauthjwttemplate.core.service.dto.user.AuthResponse;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.LoginRequest;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.SignupRequest;

public interface AuthenticationService {
    AuthResponse register(SignupRequest signupRequest);

    AuthResponse login(LoginRequest loginRequest);
}
