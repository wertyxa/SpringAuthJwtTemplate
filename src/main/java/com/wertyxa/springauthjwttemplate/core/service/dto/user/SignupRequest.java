package com.wertyxa.springauthjwttemplate.core.service.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.wertyxa.springauthjwttemplate.core.utils.annotations.PasswordMatches;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@PasswordMatches()
@Builder(toBuilder = true)
public class SignupRequest {
    @NotEmpty(message = "Please enter you Username")
    private String username;
    @NotEmpty(message = "Please enter you First Name")
    private String firstName;
    @NotEmpty(message = "Please enter you Last Name")
    private String lastName;
    @NotEmpty(message = "Please enter you Password")
    @Size(min = 6)
    private String password;
    private String confirmPassword;
}
