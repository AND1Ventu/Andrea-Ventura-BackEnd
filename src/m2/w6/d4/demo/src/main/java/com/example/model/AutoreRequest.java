package com.example.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AutoreRequest {

    @NotNull(message = "Nome obbligatorio")
    @NotEmpty(message = "Nome obbligatorio")
    private String nome;

    @NotNull(message = "Cognome obbligatorio")
    @NotEmpty(message = "Cognome obbligatorio")
    private String cognome;

    @NotNull(message = "Email obbligatorio")
    @NotEmpty(message = "Email obbligatorio")
    private String email;

    @NotNull(message = "data nascita obbligatorio")
    @NotEmpty(message = "data nascita obbligatorio")
    private LocalDate dataNascita;

}
