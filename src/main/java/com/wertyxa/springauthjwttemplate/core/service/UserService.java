package com.wertyxa.springauthjwttemplate.core.service;

import com.wertyxa.springauthjwttemplate.core.dao.entity.User;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.SignupRequest;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.UserDto;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.UserShortDto;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.UserUpdateDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {
    User create(SignupRequest dto);
    UserDto update(UserUpdateDto dto);
    User get(Long id);
    User get(String username);
    List<UserShortDto> all(Map<String, String> params);
    void delete(Long id);
}
