package com.example.demo.controller;

import com.example.demo.model.Autore;
import com.example.demo.service.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class AutoreController {

    @Autowired
    private AutoreService autoreService;

    @GetMapping("/autori")
    public List<Autore> getAllAuthors() {
        return autoreService.cercaTuttiIAutore();
    }

    @GetMapping("/autori/{id}")
    public ResponseEntity<Autore> getAuthorById(@PathVariable int id) {
        try {
            Autore author = autoreService.cercaAutorePerId(id).getAutore();
            return new ResponseEntity<>(author, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/autori")
    public ResponseEntity<Autore> createAuthor(@RequestBody Autore author) {
        Autore createdAuthor = autoreService.salvaAutore(author);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    @PutMapping("/autori/{id}")
    public ResponseEntity<Autore> updateAuthor(@PathVariable int id, @RequestBody Autore author) {
        try {
            Autore updatedAuthor = autoreService.aggiornaAutore(id, author);
            return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/autori/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
        if (autoreService.cercaAutorePerId(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

