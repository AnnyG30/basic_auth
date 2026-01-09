package com.example.BasicAuthorization.register.domain.service;


import com.example.BasicAuthorization.register.app.RegisterUser;
import com.example.BasicAuthorization.register.app.UserAlreadyExistsException;
import com.example.BasicAuthorization.user.domain.models.UserApp;
import com.example.BasicAuthorization.user.domain.models.UserRole;
import com.example.BasicAuthorization.user.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserApp register(RegisterUser command) {
        if (userRepository.existsByUsername(command.getUsername())) {
            throw new UserAlreadyExistsException(command.getUsername());
        }

        UserApp newUser = UserApp.builder()
                .username(command.getUsername())
                .password(passwordEncoder.encode(command.getPassword()))
                .role(UserRole.USER) // usuario normal por defecto
                .build();

        return userRepository.save(newUser);
    }
}
