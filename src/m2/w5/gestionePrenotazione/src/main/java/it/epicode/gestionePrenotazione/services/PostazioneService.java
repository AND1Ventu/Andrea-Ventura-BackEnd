package it.epicode.gestionePrenotazione.services;

import it.epicode.gestionePrenotazione.model.Postazione;
import it.epicode.gestionePrenotazione.model.TipoPostazione;
import it.epicode.gestionePrenotazione.repository.PostazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostazioneService {

    private final PostazioneRepository postazioneRepository;

    @Autowired
    public PostazioneService(PostazioneRepository postazioneRepository) {
        this.postazioneRepository = postazioneRepository;
    }

    public List<Postazione> ricercaPostazioniPerTipoECitta(TipoPostazione tipo, String citta) {
        return postazioneRepository.findByTipoAndCitta(tipo, citta);
    }

    public Postazione savePostazione(Postazione postazione) {
        return postazioneRepository.save(postazione);
    }

    public void deletePostazione(int id) {
        postazioneRepository.deleteById(id);
    }

    public void updatePostazione(Postazione postazione){
        Postazione p = postazioneRepository.findById(Math.toIntExact(postazione.getId())).orElse(null);

        if (p != null) {
            p.setDescrizione(postazione.getDescrizione());
            p.setTipo(postazione.getTipo());
            p.setEdificio(postazione.getEdificio());
            postazioneRepository.save(p);
        }
    }

    public List<Postazione> getAllPostazioni() {
        return postazioneRepository.findAll();
    }

    public Postazione getPostazioneById(int id) {
        return postazioneRepository.findById(id).orElse(null);
    }

}

