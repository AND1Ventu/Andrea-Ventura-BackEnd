package it.epicode.gestionePrenotazione.repository;

import it.epicode.gestionePrenotazione.model.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdificioRepository extends JpaRepository<Edificio, Integer> {

}
