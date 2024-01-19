package org.example;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class ElementoCatalogo implements Serializable {

    private String isbn;
    private String titolo;
    private int annoPubblicazione;
    private int numeroPagine;


    private static final Set<String> isbnSet = new HashSet<>();

    public ElementoCatalogo(String isbn, String titolo, int annoPubblicazione, int numeroPagine) {
        checkDuplicateIsbn(isbn);

        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;

        isbnSet.add(isbn);
    }

    private void checkDuplicateIsbn(String isbn) {
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
        return "ISBN: " + isbn + ", Titolo: " + titolo + ", Anno Pubblicazione: " + annoPubblicazione + ", Pagine: " + numeroPagine;
    }
}


