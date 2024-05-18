package com.wertyxa.springauthjwttemplate.core.web.controllers;

import com.wertyxa.springauthjwttemplate.core.service.UserService;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.UserShortDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rest/main/v1")
public class HomeControllerV1 {
    private final UserService userService;

    public HomeControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserShortDto> users(){
        return userService.all(Collections.emptyMap());
    }
}
