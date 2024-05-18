package com.wertyxa.springauthjwttemplate.core.service;

import com.wertyxa.springauthjwttemplate.core.dao.entity.UserRole;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.RoleDto;

import java.util.List;
import java.util.Map;

public interface UserRoleService {
    RoleDto create(RoleDto dto);
    RoleDto get(Long id);
    List<RoleDto> all(Map<String, String> params);
    UserRole getEnt(Long id);
    void delete(Long id);

    UserRole get(String newUser);
}
