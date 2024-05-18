package com.wertyxa.springauthjwttemplate.core.dao.repository;

import com.wertyxa.springauthjwttemplate.core.dao.entity.User;
import com.wertyxa.springauthjwttemplate.core.service.dto.user.UserShortDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameIgnoreCase(String username);

    boolean existsByUsernameIgnoreCase(String username);

    @Query(
        """
        select new com.wertyxa.springauthjwttemplate.core.service.dto.user.UserShortDto(u.id, u.username, u.firstName, u.lastName, u.fullName) from User u
        """
    )
    List<UserShortDto> findAllInShortDto();
}