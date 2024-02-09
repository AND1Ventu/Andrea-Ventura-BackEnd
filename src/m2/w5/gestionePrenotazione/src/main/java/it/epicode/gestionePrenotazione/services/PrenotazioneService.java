package it.epicode.gestionePrenotazione.services;

import it.epicode.gestionePrenotazione.model.Prenotazione;
import it.epicode.gestionePrenotazione.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;

    @Autowired
    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
    }

    public Prenotazione savePrenotazione(Prenotazione prenotazione) {
        return prenotazioneRepository.save(prenotazione);
    }

    public void deletePrenotazione(int id) {
        prenotazioneRepository.deleteById(id);
    }

    public void updatePrenotazione(Prenotazione prenotazione){
        Prenotazione p = prenotazioneRepository.findById(Math.toIntExact(prenotazione.getId())).orElse(null);

        if (p != null) {
            p.setDataPrenotazione(prenotazione.getDataPrenotazione());
            p.setPostazione(prenotazione.getPostazione());
            p.setUtente(prenotazione.getUtente());
            prenotazioneRepository.save(p);
        }
    }

    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazione getPrenotazioneById(int id) {
        return prenotazioneRepository.findById(id).orElse(null);
    }

}

