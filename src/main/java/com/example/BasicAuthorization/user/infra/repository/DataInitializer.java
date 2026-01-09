package com.example.BasicAuthorization.user.infra.repository;

import com.example.BasicAuthorization.user.domain.models.UserApp;
import com.example.BasicAuthorization.user.domain.models.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initUsers() {
        return args -> {
            String adminUsername = "admin";
            if (!userRepository.existsByUsername(adminUsername)) {
                UserApp admin = UserApp.builder()
                        .username(adminUsername)
                        .password(passwordEncoder.encode("admin")) // contraseña: admin
                        .role(UserRole.ADMIN)
                        .build();

                userRepository.save(admin);
            }
        };
    }
}
