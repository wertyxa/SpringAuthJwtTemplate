package com.wertyxa.springauthjwttemplate.core.service.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class  UserShortDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String fullName;
}
