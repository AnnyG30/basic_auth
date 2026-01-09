package com.example.BasicAuthorization.login.infra.Controller;


import com.example.BasicAuthorization.login.app.AuthenticatedUser;
import com.example.BasicAuthorization.user.domain.models.UserApp;
import com.example.BasicAuthorization.user.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;
    /**
     * Devuelve info del usuario autenticado.
     * Se accede con Basic Auth: Authorization: Basic base64(username:password)
     */
    @GetMapping("/login")
    public AuthenticatedUser me(Authentication authentication) {
        String username = authentication.getName();

        UserApp user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));

        return new AuthenticatedUser(user.getUsername(), user.getRole().name());
    }
}
