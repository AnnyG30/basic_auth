package com.example.BasicAuthorization.user.infra.repository;


import com.example.BasicAuthorization.user.domain.models.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserApp, Long> {

    Optional<UserApp> findByUsername(String username);

    boolean existsByUsername(String username);
}
