package dev.archimedes.jwtspringsecurity.controllers;

import dev.archimedes.jwtspringsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserRepository repository;
    @GetMapping("/current-user")
    public ResponseEntity<String> getUser(Principal principal){
        String userName = repository.findByEmail(principal.getName()).getName();
        return new ResponseEntity<>("<h1>" + userName + "</h1", HttpStatus.OK);
    }

}
