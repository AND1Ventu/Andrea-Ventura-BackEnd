package com.example.demo.service;

import com.example.demo.model.Autore;
import com.example.demo.model.BlogPost;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class AutoreService {

    private List<Autore> autori = new ArrayList<>();

    public List<Autore> cercaTuttiIAutore(){
        return autori;
    }

    public BlogPost cercaAutorePerId(int id) throws NoSuchElementException {
        Optional<Autore> a = autori.stream().filter(autore -> autore.getId()==id).findAny();

        if(a.isPresent()){
            return a.get();
        }
        else{
            throw new NoSuchElementException("BlogPost non trovata");
        }
    }

    public void salvaAutore(Autore autore){
        autori.add(autore);
    }

    public BlogPost aggiornaAutore(int id, Autore autore) throws NoSuchElementException{
        Autore a = cercaAutorePerId(id);

        a.setNome(autore.getNome());
        a.setCognome(autore.getCognome());
        a.setEmail(autore.getEmail());
        a.setDataDiNascita(autore.getDataDiNascita());
        a.setAvatar(autore.getAvatarUrl());

        return a;
    }

    public void cancellaAutore(int id) throws NoSuchElementException{
        Autore a = cercaAutorePerId(id);

        autori.remove(a);
    }
}