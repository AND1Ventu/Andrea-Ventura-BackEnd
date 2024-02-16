package com.example.controller;

import com.example.model.Autore;
import com.example.service.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AutoreController {

    @Autowired
    private AutoreService autoreService;

    @GetMapping("/autori")
    public List<Autore> getAllAuthors() {
        return autoreService.findAll();
    }

    @GetMapping("/autori/{id}")
    public ResponseEntity<Autore> getAuthorById(@PathVariable Long id) {
        Optional<Autore> author = autoreService.getAuthorById(id);
        return author.map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/autori")
    public ResponseEntity<Autore> createAuthor(@RequestBody Autore author) {
        Autore createdAuthor = autoreService.saveAuthor(author);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    @PutMapping("/autori/{id}")
    public ResponseEntity<Autore> updateAuthor(@PathVariable Long id, @RequestBody Autore updatedAuthor) {
        Optional<Autore> updated = Optional.ofNullable(autoreService.updateAuthor(id, updatedAuthor));
        return updated.map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/autori/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        autoreService.deleteAuthor(id); // Assumes service handles non-existent case
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

