package com.example.BasicAuthorization.register.app;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String username) {
        super("El usuario '" + username + "' ya existe");
    }
}
