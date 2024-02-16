package com.example.GestioneDispositivi.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String avatar;


    @OneToMany(mappedBy = "dipendente", cascade = CascadeType.ALL)
    private List<Dispositivo> dispositivi = new ArrayList<>();

    public Dipendente(String username, String nome, String cognome, String email){
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }
}
