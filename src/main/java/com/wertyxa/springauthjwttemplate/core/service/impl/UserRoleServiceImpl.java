package com.wertyxa.springauthjwttemplate.core.service.impl;

import com.wertyxa.springauthjwttemplate.core.dao.entity.UserRole;
import com.wertyxa.springauthjwttemplate.core.dao.repository.UserRoleRepository;
import com.wertyxa.springauthjwttemplate.core.service.UserRoleService;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.RoleDto;
import com.wertyxa.springauthjwttemplate.core.utils.exceptions.ResponseException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {
    @Value("${default.roles}")
    private Set<String> roles;
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public RoleDto create(RoleDto dto) {
        return new RoleDto(userRoleRepository.save(UserRole.builder().name(dto.getName()).build()));
    }

    @Override
    public RoleDto get(Long id) {
        return new RoleDto(getEnt(id));
    }

    @Override
    public List<RoleDto> all(Map<String, String> params) {
        return userRoleRepository.findAll().stream().map(RoleDto::new).toList();
    }

    @Override
    public UserRole getEnt(Long id) {
        return userRoleRepository.findById(id).orElseThrow(()-> new ResponseException(HttpStatus.NOT_FOUND, id.toString(), "ROLE_BY_ID_NOT_FOUND"));
    }

    @Override
    public void delete(Long id) {
        userRoleRepository.delete(getEnt(id));
    }

    @Override
    public UserRole get(String role) {
        return userRoleRepository.findByNameIgnoreCase(role).orElseThrow(
                () -> new ResponseException(HttpStatus.NOT_FOUND, role, "ROLE_NOT_FOUND"));
    }
    @Transactional
    @PostConstruct
    public void loadDefaultRole(){
        List<UserRole> all = userRoleRepository.findAll();
        userRoleRepository.saveAll(roles.stream().filter(role -> all.stream().noneMatch(r -> r.getName().equalsIgnoreCase(role))).map(UserRole::new).toList());
        log.info("Loaded default role");
    }
}
