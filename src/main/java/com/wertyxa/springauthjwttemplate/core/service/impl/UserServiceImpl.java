package com.wertyxa.springauthjwttemplate.core.service.impl;


import com.wertyxa.springauthjwttemplate.core.dao.entity.User;
import com.wertyxa.springauthjwttemplate.core.dao.repository.UserRepository;
import com.wertyxa.springauthjwttemplate.core.service.UserRoleService;
import com.wertyxa.springauthjwttemplate.core.service.UserService;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.SignupRequest;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.UserDto;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.UserShortDto;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.UserUpdateDto;
import com.wertyxa.springauthjwttemplate.core.utils.exceptions.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;

    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
    }

    @Override
    public User loadUserByUsername(String username){
        return userRepository.findByUsernameIgnoreCase(username).orElseThrow(
                () -> new ResponseException(HttpStatus.NOT_FOUND, username,"USER_NOT_FOUND"));
    }

    @Override
    public User create(SignupRequest dto) {
        if (userRepository.existsByUsernameIgnoreCase(dto.getUsername())) throw new ResponseException(HttpStatus.BAD_REQUEST, dto.getUsername(),"USER_USERNAME_EXISTS");
        long count = userRepository.count();
        User newUser = userRepository.save(User.builder()
                .username(dto.getUsername())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .fullName(dto.getFirstName() + " " + dto.getLastName())
                .roles(List.of(userRoleService.get(count >=1 ? "NEW_USER": "ADMIN")))
                .password(dto.getPassword())
                .build());
        return newUser;
    }

    @Override
    public UserDto update(UserUpdateDto dto) {
        User user = get(dto.getId());
        dto.getFirstName().ifPresent(user::setFirstName);
        dto.getLastName().ifPresent(user::setLastName);
        dto.getIsEnabled().ifPresent(user::setEnabled);
        return new UserDto(userRepository.save(user));
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResponseException(HttpStatus.NOT_FOUND, id.toString(), "USER_BY_ID_NOT_FOUND"));
    }

    @Override
    public User get(String username) {
        return loadUserByUsername(username);
    }

    @Override
    public List<UserShortDto> all(Map<String, String> params) {
        return userRepository.findAllInShortDto();
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(get(id));
    }
}
