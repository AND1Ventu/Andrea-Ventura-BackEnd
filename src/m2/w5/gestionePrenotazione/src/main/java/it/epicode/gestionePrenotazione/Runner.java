package it.epicode.gestionePrenotazione;

import it.epicode.gestionePrenotazione.model.*;
import it.epicode.gestionePrenotazione.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

import java.time.LocalDate;

@Component
public class Runner implements CommandLineRunner {

    private final EdificioService edificioService;
    private final PostazioneService postazioneService;
    private final UtenteService utenteService;
    private final PrenotazioneService prenotazioneService;

    private final Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);


    public Runner(EdificioService edificioService, PostazioneService postazioneService, UtenteService utenteService, PrenotazioneService prenotazioneService) {
        this.edificioService = edificioService;
        this.postazioneService = postazioneService;
        this.utenteService = utenteService;
        this.prenotazioneService = prenotazioneService;
    }

    @Override
    public void run(String... args) {
        try {
            // Creazione di un edificio
            try {
                Edificio edificio = new Edificio();
                edificio.setNome("Edificio A");
                edificio.setIndirizzo("Via Roma, 123");
                edificio.setCitta("Bologna");
                edificio.setNumeroMassimoOccupanti(100);

                edificio = edificioService.saveEdificio(edificio);
                logger.info("Edificio creato sul database");
            } catch (Exception edificioException) {
                logger.error("Errore durante la creazione dell'edificio:", edificioException);
            }

            // Creazione di una postazione associata all'edificio
            try {
                Postazione postazione = new Postazione();
                postazione.setDescrizione("Postazione 1");
                postazione.setTipo(TipoPostazione.SALA_RIUNIONI);
                postazione.setEdificio(edificioService.getEdificioById(1));

                postazione = postazioneService.savePostazione(postazione);
                logger.info("Postazione creata ");
            } catch (Exception postazioneException) {
                logger.error("Errore durante la creazione della postazione:", postazioneException);
            }

            // Creazione di un utente
            try {
                Utente utente = new Utente();
                utente.setUsername("AndreaVentura");
                utente.setNomeCompleto("Andrea Ventura");
                utente.setEmail("andrea@ventura.com");

                utente = utenteService.saveUtente(utente);
                logger.info("Utente creato");
            } catch (Exception utenteException) {
                logger.error("Errore durante la creazione dell'utente:", utenteException);
            }

            // Creazione di una prenotazione associata all'utente e alla postazione
            try {
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setDataPrenotazione(LocalDate.now());
                prenotazione.setUtente(utenteService.getUtenteById(0));
                prenotazione.setPostazione(postazioneService.getPostazioneById(1));

                prenotazione = prenotazioneService.savePrenotazione(prenotazione);
                logger.info("Prenotazione creata");
            } catch (Exception prenotazioneException) {
                logger.error("Errore durante la creazione della prenotazione:", prenotazioneException);
            }

            logger.info("Records salvati con successo!");


            // Ricerca di postazioni per tipo e città
            try {
                TipoPostazione tipo = TipoPostazione.SALA_RIUNIONI;
                String citta = "Bologna";
                List<Postazione> postazioni = postazioneService.ricercaPostazioniPerTipoECitta(tipo, citta);

                if (!postazioni.isEmpty()) {
                    logger.info("Postazioni trovate per tipo {} e città {}: {}", tipo, citta, postazioni);
                } else {
                    logger.info("Nessuna postazione trovata per tipo {} e città {}", tipo, citta);
                }
            } catch (Exception ricercaException) {
                logger.error("Errore durante la ricerca delle postazioni:", ricercaException);
            }


        } catch (Exception e) {
            logger.error("An error occurred during application run:", e);
        }


    }
}
