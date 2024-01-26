package org.example.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.Set;
import java.util.HashSet;


@Entity
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;
    @Column(name = "data_nascita")
    private String dataNascita;

    @Column(name = "numero_tessera")
    private String numeroTessera;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private Set<Prestito> prestiti = new HashSet<>();

    public Utente() {
    }

    public Utente(Long id, String nome, String cognome, String dataNascita, String numeroTessera) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.numeroTessera = numeroTessera;
    }

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public java.lang.String getNome() {
        return nome;
    }

    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }

    public java.lang.String getCognome() {
        return cognome;
    }

    public void setCognome(java.lang.String cognome) {
        this.cognome = cognome;
    }

    public java.lang.String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(java.lang.String dataNascita) {
        this.dataNascita = dataNascita;
    }

    public java.lang.String getNumeroTessera() {
        return numeroTessera;
    }

    public void setNumeroTessera(java.lang.String numeroTessera) {
        this.numeroTessera = numeroTessera;
    }
}

