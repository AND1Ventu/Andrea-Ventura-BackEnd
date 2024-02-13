package com.example.demo.model;

import lombok.Data;

@Data
public class Autore {
    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String dataDiNascita;
    private String avatar;

    public String getAvatarUrl() {
        return "https://ui-avatars.com/api/?name=" + nome + "+" + cognome;
    }
}
