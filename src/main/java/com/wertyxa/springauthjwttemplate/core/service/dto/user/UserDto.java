package com.wertyxa.springauthjwttemplate.core.service.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.wertyxa.springauthjwttemplate.core.dao.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder(toBuilder = true)
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String fullName;
    @Builder.Default
    private List<RoleDto> roles = new ArrayList<>();

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.fullName = user.getFullName();
        this.roles = user.getRoles().stream().map(RoleDto::new).toList();
    }
}
