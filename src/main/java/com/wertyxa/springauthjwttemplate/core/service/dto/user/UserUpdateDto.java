package com.wertyxa.springauthjwttemplate.core.service.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserUpdateDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Boolean isEnabled;

    public Optional<String> getFirstName() {
        return Optional.ofNullable(firstName);
    }
    public Optional<String> getLastName() {
        return Optional.ofNullable(lastName);
    }
    public Optional<Boolean> getIsEnabled() {
        return Optional.ofNullable(isEnabled);
    }
}
