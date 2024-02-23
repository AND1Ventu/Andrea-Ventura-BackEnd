package com.exercise.gestioneEventi.dto;

import com.exercise.gestioneEventi.model.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "username obbligatorio")
    private String username;
    @NotBlank(message = "password obbligatoria")
    private String password;

}
