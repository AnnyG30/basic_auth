package com.example.BasicAuthorization.login.app;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticatedUser {

    private String username;
    private String role;
}
