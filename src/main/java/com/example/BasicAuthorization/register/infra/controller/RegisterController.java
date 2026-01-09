package com.example.BasicAuthorization.register.infra.controller;


import com.example.BasicAuthorization.register.app.RegisterUser;
import com.example.BasicAuthorization.register.app.UserAlreadyExistsException;
import com.example.BasicAuthorization.register.domain.service.RegisterService;
import com.example.BasicAuthorization.user.domain.models.UserApp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUser request) {
        try {
            UserApp saved = registerService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new RegisterResponse(saved.getId(), saved.getUsername(), saved.getRole().name()));
        } catch (UserAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @Getter
    @AllArgsConstructor
    static class RegisterResponse {
        private Long id;
        private String username;
        private String role;
    }
}
