package org.example.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ElementoCatalogo implements Serializable {

    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "anno_pubblicazione")
    private int annoPubblicazione;

    @Column(name = "numero_pagine")
    private int numeroPagine;

    @OneToMany(mappedBy = "elementoPrestato", cascade = CascadeType.ALL)
    private Set<Prestito> prestiti = new HashSet<>();

    private static final Set<String> isbnSet = new HashSet<>();

    public ElementoCatalogo(String isbn, String titolo, int annoPubblicazione, int numeroPagine) {
        checkDuplicateIsbn(isbn);

        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;

        isbnSet.add(isbn);
    }

    protected ElementoCatalogo() {
    }

    public static String generateIsbn() {
        String newIsbn;
        do {
            UUID uuid = UUID.randomUUID();
            String uuidString = uuid.toString().replaceAll("-", "");
            newIsbn = uuidString.substring(0, 12);
        } while (isbnSet.contains(newIsbn));

        checkDuplicateIsbn(newIsbn);
        return newIsbn;
    }

    private static void checkDuplicateIsbn(String isbn) {
        if (isbnSet.contains(isbn)) {
            throw new IllegalArgumentException("ISBN duplicato: " + isbn);
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + ", Titolo: " + titolo + ", Anno Pubblicazione: " + annoPubblicazione + ", Pagine: "
                + numeroPagine;
    }
}
