package it.epicode.gestionePrenotazione.services;

import it.epicode.gestionePrenotazione.model.Utente;
import it.epicode.gestionePrenotazione.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;

    @Autowired
    public UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public Utente saveUtente(Utente utente) {
        return utenteRepository.save(utente);
    }

    public void deleteUtente(int id) {
        utenteRepository.deleteById(String.valueOf(id));
    }

    public void updateUtente(Utente utente){
        Utente u = utenteRepository.findById(String.valueOf(utente.getId())).orElse(null);

        if (u != null) {
            u.setUsername(utente.getUsername());
            u.setNomeCompleto(utente.getNomeCompleto());
            u.setEmail(utente.getEmail());
            utenteRepository.save(u);
        }
    }

    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    public Utente getUtenteById(int id) {
        return utenteRepository.findById(String.valueOf(id)).orElse(null);
    }

}
