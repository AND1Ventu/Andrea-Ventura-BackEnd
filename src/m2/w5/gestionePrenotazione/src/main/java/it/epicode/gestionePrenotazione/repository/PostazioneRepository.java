package it.epicode.gestionePrenotazione.repository;

import it.epicode.gestionePrenotazione.model.Postazione;
import it.epicode.gestionePrenotazione.model.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, Integer> {

    @Query("SELECT p FROM Postazione p JOIN p.edificio e WHERE p.tipo = :tipo AND e.citta = :citta")
    List<Postazione> findByTipoAndCitta(@Param("tipo") TipoPostazione tipo, @Param("citta") String citta);
}
