package dev.archimedes.jwtspringsecurity.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "APP_USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USERID")
    private long id;

    @Column(name = "USERNAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    private String password;

    @Column(name = "PHONE")
    private String phone;
}
