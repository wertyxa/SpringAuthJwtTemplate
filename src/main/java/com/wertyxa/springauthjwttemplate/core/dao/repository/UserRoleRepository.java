package com.wertyxa.springauthjwttemplate.core.dao.repository;


import com.wertyxa.springauthjwttemplate.core.dao.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByNameIgnoreCase(String name);
}