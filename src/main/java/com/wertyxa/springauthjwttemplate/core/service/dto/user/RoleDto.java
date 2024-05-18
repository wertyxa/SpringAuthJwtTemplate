package com.wertyxa.springauthjwttemplate.core.service.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.wertyxa.springauthjwttemplate.core.dao.entity.UserRole;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
public class RoleDto {
    private Long id;
    private String name;

    public RoleDto(UserRole role){
        this.id = role.getId();
        this.name = role.getName();
    }
}
