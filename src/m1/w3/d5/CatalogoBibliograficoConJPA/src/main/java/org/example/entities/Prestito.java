package org.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "elemento_prestato_id")
    private ElementoCatalogo elementoPrestato;

    @Column(name = "data_inizio_prestito")
    private String dataInizioPrestito;

    @Column(name = "data_restituzione_prevista")
    private String dataRestituzionePrevista;

    @Column(name = "data_restituzione_effettiva")
    private String dataRestituzioneEffettiva;

    public Prestito() {
    }

    public Prestito(java.lang.Long id, Utente utente, ElementoCatalogo elementoPrestato, java.lang.String dataInizioPrestito, java.lang.String dataRestituzionePrevista, java.lang.String dataRestituzioneEffettiva) {
        this.id = id;
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
        this.dataInizioPrestito = dataInizioPrestito;
        LocalDate startDate = LocalDate.parse(dataInizioPrestito);
        this.dataRestituzionePrevista = startDate.plusDays(30).toString();
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public ElementoCatalogo getElementoPrestato() {
        return elementoPrestato;
    }

    public void setElementoPrestato(ElementoCatalogo elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
    }

    public java.lang.String getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(java.lang.String dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public java.lang.String getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(java.lang.String dataRestituzionePrevista) {
        LocalDate startDate = LocalDate.parse(dataInizioPrestito);
        LocalDate previstaDate = startDate.plusDays(30);
        this.dataRestituzionePrevista = previstaDate.toString();
    }

    public java.lang.String getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(java.lang.String dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }
}

