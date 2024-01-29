package org.example;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        // Creazione di un'istanza di CatalogoBibliotecario
        CatalogoBibliotecario catalogoBibliotecario = new CatalogoBibliotecario();

        // Aggiunta di elementi
        Libro libro1 = new Libro("ISBN123", "Titolo Libro 1", 2022, 200, "Autore 1", "Genere 1");
        catalogoBibliotecario.aggiungiElemento(libro1);

        Rivista rivista1 = new Rivista("ISBN456", "Titolo Rivista 1", 2022, 50, Rivista.Periodicita.MENSILE);
        catalogoBibliotecario.aggiungiElemento(rivista1);

        // Ricerca per ISBN
        ElementoCatalogo risultatoRicercaIsbn = catalogoBibliotecario.ricercaPerIsbn("ISBN123");

        // Ricerca per anno di pubblicazione
        List<ElementoCatalogo> risultatoRicercaAnno = catalogoBibliotecario.ricercaPerAnnoPubblicazione(2022);

        // Ricerca per autore
        List<ElementoCatalogo> risultatoRicercaAutore = catalogoBibliotecario.ricercaPerAutore("Autore 1");

        // Salvataggio su disco
        List<ElementoCatalogo> catalogoDaSalvare = catalogoBibliotecario.getCatalogo();
        CatalogoBibliotecario.salvaSuDisco(catalogoDaSalvare, "catalogo.txt");

        // Caricamento da disco
        List<ElementoCatalogo> catalogoCaricato = CatalogoBibliotecario.caricaDaDisco("catalogo.txt");
    }
}
