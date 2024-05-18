package com.wertyxa.springauthjwttemplate.core.utils;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.wertyxa.springauthjwttemplate.core.dao.entity.User;
import com.wertyxa.springauthjwttemplate.core.dao.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RequestUser {
    private Long id;
    private String username;
    private List<String> roles;

    public RequestUser(User userFromPrincipal) {
        this.id = userFromPrincipal.getId();
        this.username = userFromPrincipal.getUsername();
        this.roles = userFromPrincipal.getRoles().stream().map(UserRole::getName).toList();
    }
}
