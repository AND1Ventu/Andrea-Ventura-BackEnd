package com.example.GestioneDispositivi.service;

import com.example.GestioneDispositivi.exception.NotFoundException;
import com.example.GestioneDispositivi.model.Dipendente;
import com.example.GestioneDispositivi.model.DipendenteRequest;
import com.example.GestioneDispositivi.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Page<Dipendente> findAllEmployees(Pageable pageable){
        return dipendenteRepository.findAll((org.springframework.data.domain.Pageable) pageable);
    }

    public Dipendente findById(int id){
        return dipendenteRepository.findById(id).
                orElseThrow(()-> new NotFoundException("DIpendente con ID=" + id + " non trovato"));
    }

    public Dipendente saveEmployee(DipendenteRequest dipendenteRequest){
        Dipendente dipendente = new Dipendente(
                dipendenteRequest.getUsername(),
                dipendenteRequest.getNome(),
                dipendenteRequest.getCognome(),
                dipendenteRequest.getEmail()
        );
        return dipendenteRepository.save(dipendente);
    }

    public Dipendente updateEmployee(int id, DipendenteRequest dipendenteRequest) throws NotFoundException{
        Dipendente a = findById(id);

        a.setUsername(dipendenteRequest.getUsername());
        a.setNome(dipendenteRequest.getNome());
        a.setCognome(dipendenteRequest.getCognome());
        a.setEmail(dipendenteRequest.getEmail());

        return dipendenteRepository.save(a);
    }

    public void deleteEmployee(int id) throws NotFoundException{
        Dipendente d = findById(id);
        dipendenteRepository.delete(d);
    }

    public Dipendente uploadAvatar(int id, String url){
        Dipendente dipendente = findById(id);
        dipendente.setAvatar(url);
        return dipendenteRepository.save(dipendente);
    }
}
