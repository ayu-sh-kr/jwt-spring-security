package dev.archimedes.jwtspringsecurity.services;

import dev.archimedes.jwtspringsecurity.dtos.SignUpRequest;
import dev.archimedes.jwtspringsecurity.dtos.UserDTO;
import dev.archimedes.jwtspringsecurity.models.User;
import dev.archimedes.jwtspringsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        user.setPhone(signUpRequest.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));

        User createdUser = userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(createdUser.getId());
        userDTO.setName(createdUser.getName());
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setPhone(createdUser.getPhone());
        return userDTO;
    }
}
