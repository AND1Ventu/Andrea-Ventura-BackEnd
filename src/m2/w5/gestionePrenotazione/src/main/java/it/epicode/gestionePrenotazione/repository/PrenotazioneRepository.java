package it.epicode.gestionePrenotazione.repository;

import it.epicode.gestionePrenotazione.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {

}
