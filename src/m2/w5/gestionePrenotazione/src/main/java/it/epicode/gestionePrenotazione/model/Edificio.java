package it.epicode.gestionePrenotazione.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Edificio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String indirizzo;
    private String citta;
    private int numeroMassimoOccupanti;

    @OneToMany(mappedBy = "edificio")
    private List<Postazione> postazioni;

    @Override
    public String toString() {
        return "Edificio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", citta='" + citta + '\'' +
                ", numeroMassimoOccupanti=" + numeroMassimoOccupanti +
                '}';
    }
}
