package com.example.GestioneDispositivi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DispositivoRequest {

    @NotNull(message = "Tipo obbligatorio")
    @NotEmpty(message = "Tipo obbligatorio")
    private String tipo;
    private StatoDispositivo stato;

    @NotNull(message = "Dipendente obbligatorio")
    private Dipendente dipendente;
}
