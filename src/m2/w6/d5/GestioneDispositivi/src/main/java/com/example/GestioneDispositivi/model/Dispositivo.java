package com.example.GestioneDispositivi.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;

    @Enumerated(EnumType.STRING)
    private StatoDispositivo stato;


    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    public Dispositivo(String tipo, StatoDispositivo stato, Dipendente dipendente) {
        this.tipo = tipo;
        this.stato = stato;
        this.dipendente = dipendente;
    }
}
