package it.epicode.gestionePrenotazione.services;

import it.epicode.gestionePrenotazione.model.Edificio;
import it.epicode.gestionePrenotazione.repository.EdificioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdificioService {

    private final EdificioRepository edificioRepository;

    @Autowired
    public EdificioService(EdificioRepository edificioRepository) {
        this.edificioRepository = edificioRepository;
    }

    public Edificio saveEdificio(Edificio edificio) {
        return edificioRepository.save(edificio);
    }

    public void deleteEdificio(int id) {
        edificioRepository.deleteById(id);
    }

    public void updateEdificio(Edificio edificio){
        Edificio e = edificioRepository.findById(Math.toIntExact(edificio.getId())).get();
        e.setNome(edificio.getNome());
        e.setCitta(edificio.getCitta());
        e.setIndirizzo(edificio.getIndirizzo());
        e.setNumeroMassimoOccupanti(edificio.getNumeroMassimoOccupanti());
        edificioRepository.save(e);
    }

    public List<Edificio> getAllEdifici() {
        return edificioRepository.findAll();
    }

    public Edificio getEdificioById(int id) {
        return edificioRepository.findById(id).orElse(null);
    }


}

