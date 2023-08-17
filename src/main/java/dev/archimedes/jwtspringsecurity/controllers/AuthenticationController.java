package dev.archimedes.jwtspringsecurity.controllers;

import dev.archimedes.jwtspringsecurity.dtos.AuthenticationRequest;
import dev.archimedes.jwtspringsecurity.dtos.AuthenticationResponse;
import dev.archimedes.jwtspringsecurity.services.jwt.UserDetailServiceImpl;
import dev.archimedes.jwtspringsecurity.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;

@RestController
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authentication")
    public AuthenticationResponse createToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse httpServletResponse)
    throws BadCredentialsException, DisabledException, IOException, UsernameNotFoundException {
        try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect Username or Password");
        }catch (DisabledException e){
            httpServletResponse.sendError(httpServletResponse.SC_NOT_FOUND, "User is not registered, register first");
            return null;
        }

        final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return new AuthenticationResponse(jwt);
    }

//    @GetMapping("/current-user")
//    public ResponseEntity<String> geUser(Principal principal){
//        log.info(principal.getName());
//        return new ResponseEntity<>("<h1>Hello " + principal.getName() + "</h1>", HttpStatus.ACCEPTED);
//    }
}
