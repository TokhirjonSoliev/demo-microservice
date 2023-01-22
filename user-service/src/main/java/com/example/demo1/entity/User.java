package com.example.demo1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false)
    @EqualsAndHashCode.Exclude
    private UUID id;

    @Size(min = 1, max = 100)
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 1, max = 100)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    @Column(name = "password")
    private String password;

}
