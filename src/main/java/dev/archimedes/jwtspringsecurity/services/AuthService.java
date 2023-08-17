package dev.archimedes.jwtspringsecurity.services;

import dev.archimedes.jwtspringsecurity.dtos.SignUpRequest;
import dev.archimedes.jwtspringsecurity.dtos.UserDTO;

public interface AuthService {
    UserDTO createUser(SignUpRequest signUpRequest);
}
