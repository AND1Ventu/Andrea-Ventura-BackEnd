package com.example.demo.model;

import lombok.Data;

@Data
public class BlogPost {
    private Long id;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;

    private Autore autore;
}
