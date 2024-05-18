package com.wertyxa.springauthjwttemplate.core.service.impl;

import com.wertyxa.springauthjwttemplate.config.JwtUtil;
import com.wertyxa.springauthjwttemplate.core.dao.entity.User;
import com.wertyxa.springauthjwttemplate.core.service.AuthenticationService;
import com.wertyxa.springauthjwttemplate.core.service.UserService;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.AuthResponse;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.LoginRequest;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.SignupRequest;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.UserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse register(SignupRequest signupRequest) {
        var user = userService.create(
                signupRequest.toBuilder()
                        .password(passwordEncoder.encode(signupRequest.getPassword()))
                        .build()
        );
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getUsername(), null, new ArrayList<>()));
        return AuthResponse.builder()
                .user(new UserDto((user)))
                .token(jwtUtil.createToken(user))
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        var user = (User) authenticate.getPrincipal();
        String token = jwtUtil.createToken(user);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return AuthResponse.builder()
                .token(token)
                .user(new UserDto(user))
                .build();
    }
}
