package com.exercise.gestioneEventi.dto;

import com.exercise.gestioneEventi.model.Role;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class UserRequest {

    @NotBlank(message = "Il campo 'username' non può essere vuoto")
    @Size(min = 3, max = 50, message = "Il campo 'username' deve essere lungo tra 3 e 50 caratteri")
    private String username;

    @NotBlank(message = "Il campo 'password' non può essere vuoto")
    @Size(min = 6, message = "Il campo 'password' deve essere lungo almeno 6 caratteri")
    private String password;

    @NotNull(message = "Il campo 'userType' non può essere nullo")
    private Role role;
}
