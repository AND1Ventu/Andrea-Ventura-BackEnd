package it.epicode.gestionePrenotazione.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"data_prenotazione", "utente_id"}),
                @UniqueConstraint(columnNames = {"postazione_id", "data_prenotazione"})
        }
)
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_prenotazione")
    private LocalDate dataPrenotazione;

    @ManyToOne
    @JoinColumn(name = "postazione_id")
    private Postazione postazione;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

}
