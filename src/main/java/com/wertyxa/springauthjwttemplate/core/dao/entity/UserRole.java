package com.wertyxa.springauthjwttemplate.core.dao.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "pm_roles")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public UserRole(String name) {
        this.name = name;
    }
}