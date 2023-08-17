package dev.archimedes.jwtspringsecurity.controllers;

import dev.archimedes.jwtspringsecurity.dtos.SignUpRequest;
import dev.archimedes.jwtspringsecurity.dtos.UserDTO;
import dev.archimedes.jwtspringsecurity.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class SignUpController {

    @Autowired
    private AuthService authService;

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody SignUpRequest signUpRequest){
        UserDTO createdUser = authService.createUser(signUpRequest);

        if(createdUser == null)
            return new ResponseEntity<>("User not created", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
