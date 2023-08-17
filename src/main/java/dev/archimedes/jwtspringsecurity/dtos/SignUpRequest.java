package dev.archimedes.jwtspringsecurity.dtos;

import lombok.Data;

@Data
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
}
