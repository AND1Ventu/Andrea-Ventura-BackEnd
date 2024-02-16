package com.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "autori")
public class Autore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String dataDiNascita;
    public String getAvatarUrl() {
        return "https://ui-avatars.com/api/?name=" + nome + "+" + cognome;
    }

}
