package dev.archimedes.jwtspringsecurity.dtos;

import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String name;
    private String email;
    private String phone;
    private String password;
}
