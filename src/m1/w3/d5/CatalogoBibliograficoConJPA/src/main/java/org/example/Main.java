package org.example;

import org.example.entities.*;
import org.example.dao.*;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Scegli un'operazione:");
        System.out.println("1. Aggiungi Elemento del Catalogo");
        System.out.println("2. Rimuovi Elemento del Catalogo");
        System.out.println("3. Ricerca Elemento per ISBN");
        System.out.println("4. Ricerca Elementi per Anno di Pubblicazione");
        System.out.println("5. Ricerca Elementi per Autore");
        System.out.println("6. Ricerca Elementi per Titolo");
        System.out.println("7. Aggiungi Utente");
        System.out.println("8. Rimuovi Utente");
        System.out.println("9. Ricerca Utente per ID");
        System.out.println("10. Ricerca Utenti per Nome");
        System.out.println("11. Aggiungi Prestito");
        System.out.println("12. Rimuovi Prestito");
        System.out.println("13. Ricerca Prestito per ID");
        System.out.println("14. Ricerca Prestiti Utente");
        System.out.println("15. Ricerca Prestiti Scaduti Non Restituiti");
        System.out.println("0. Esci");
        CatalogoDAO catalogoDAO = new CatalogoDAO();
        PrestitoDAO prestitoDAO = new PrestitoDAO();
        UtenteDAO utenteDAO = new UtenteDAO();

        while (true) {


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    aggiungiElementoCatalogo(scanner, catalogoDAO);
                    break;
                case 2:
                    rimuoviElementoCatalogo(scanner, catalogoDAO);
                    break;
                case 3:
                    ricercaPerISBN(scanner, catalogoDAO);
                    break;
                case 4:
                    ricercaPerAnnoPubblicazione(scanner, catalogoDAO);
                    break;
                case 5:
                    ricercaPerAutore(scanner, catalogoDAO);
                    break;
                case 6:
                    ricercaPerTitolo(scanner, catalogoDAO);
                    break;
                case 7:
                    aggiungiUtente(scanner, utenteDAO);
                    break;
                case 8:
                    rimuoviUtente(scanner, utenteDAO);
                    break;
                case 9:
                    ricercaUtentePerId(scanner, utenteDAO);
                    break;
                case 10:
                    ricercaUtentiPerNome(scanner, utenteDAO);
                    break;
                case 11:
                    aggiungiPrestito(scanner, prestitoDAO, catalogoDAO, utenteDAO);
                    break;
                case 12:
                    rimuoviPrestito(scanner, prestitoDAO, utenteDAO);
                    break;
                case 13:
                    ricercaPrestitoPerId(scanner, prestitoDAO);
                    break;
                case 14:
                    ricercaPrestitiUtente(scanner, prestitoDAO, utenteDAO);
                    break;
                case 15:
                    ricercaPrestitiScadutiNonRestituiti(prestitoDAO);
                    break;
                case 0:
                    System.out.println("Arrivederci!");
                    System.exit(0);
                default:
                    System.out.println("Scelta non valida. Riprova.");
                    break;
            }
        }
    }


    private static void aggiungiElementoCatalogo(Scanner scanner, CatalogoDAO catalogoDAO) {
        System.out.println("Inserisci i dettagli del nuovo elemento:");
        System.out.print("Titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Anno di pubblicazione: ");
        int annoPubblicazione = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Scegli il tipo di elemento:");
        System.out.println("1. Libro");
        System.out.println("2. Rivista");
        int tipoElemento = scanner.nextInt();
        scanner.nextLine();

        ElementoCatalogo elemento;

        switch (tipoElemento) {
            case 1:
                System.out.print("Autore: ");
                String autoreLibro = scanner.nextLine();
                System.out.print("Genere: ");
                String genereLibro = scanner.nextLine();
                System.out.print("Numero Pagine: ");
                int numeroPagineLibro = scanner.nextInt();
                scanner.nextLine();

                elemento = new Libro(titolo, annoPubblicazione, numeroPagineLibro, autoreLibro, genereLibro);
                break;
            case 2:
                System.out.print("Periodicita (SETTIMANALE, MENSILE, SEMESTRALE): ");
                Rivista.Periodicita periodicita = Rivista.Periodicita.valueOf(scanner.nextLine().toUpperCase());
                System.out.print("Numero Pagine: ");
                int numeroPagineRivista = scanner.nextInt();
                scanner.nextLine();

                elemento = new Rivista(titolo, annoPubblicazione, numeroPagineRivista, periodicita);
                break;
            default:
                System.out.println("Tipo di elemento non valido.");
                return;
        }


        catalogoDAO.aggiungiElemento(elemento);
        System.out.println("Elemento aggiunto con successo!");
    }

    private static void rimuoviElementoCatalogo(Scanner scanner, CatalogoDAO catalogoDAO) {
        List<String> isbnList = catalogoDAO.getAllIsbns();
        if (isbnList.isEmpty()) {
            System.out.println("Non ci sono elementi nel catalogo.");
            return;
        }

        System.out.println("Lista dei possibili ISBN:");
        for (String isbn : isbnList) {
            System.out.println(isbn);
        }

        System.out.print("Inserisci l'ISBN dell'elemento da rimuovere: ");
        String isbnToRemove = scanner.nextLine();

        ElementoCatalogo elementoToRemove = catalogoDAO.ricercaPerIsbn(isbnToRemove);

        if (elementoToRemove != null) {
            catalogoDAO.rimuoviElemento(isbnToRemove);
            System.out.println("Elemento rimosso con successo!");
        } else {
            System.out.println("Nessun elemento trovato con l'ISBN fornito.");
        }
    }


    private static void ricercaPerISBN(Scanner scanner, CatalogoDAO catalogoDAO) {
        List<String> isbnList = catalogoDAO.getAllIsbns();
        System.out.println("Lista dei possibili ISBN:");
        for (String isbn : isbnList) {
            System.out.println(isbn);
        }

        System.out.print("Inserisci l'ISBN da cercare: ");
        String isbnToSearch = scanner.nextLine();
        ElementoCatalogo elemento = catalogoDAO.ricercaPerIsbn(isbnToSearch);

        if (elemento != null) {
            System.out.println("Elemento trovato:\n" + elemento);
        } else {
            System.out.println("Nessun elemento trovato con l'ISBN fornito.");
        }
    }


    private static void ricercaPerAnnoPubblicazione(Scanner scanner, CatalogoDAO catalogoDAO) {
        System.out.print("Inserisci l'anno di pubblicazione da cercare: ");
        int anno = scanner.nextInt();
        scanner.nextLine();

        List<ElementoCatalogo> result = catalogoDAO.ricercaPerAnnoPubblicazione(anno);

        if (!result.isEmpty()) {
            System.out.println("Elementi trovati:");
            for (ElementoCatalogo elemento : result) {
                System.out.println(elemento);
            }
        } else {
            System.out.println("Nessun elemento trovato per l'anno di pubblicazione fornito.");
        }
    }

    private static void ricercaPerAutore(Scanner scanner, CatalogoDAO catalogoDAO) {
        System.out.print("Inserisci il nome dell'autore da cercare: ");
        String autore = scanner.nextLine();

        List<ElementoCatalogo> elementi = catalogoDAO.ricercaPerAutore(autore);

        if (!elementi.isEmpty()) {
            System.out.println("Elementi trovati:");
            for (ElementoCatalogo elemento : elementi) {
                System.out.println(elemento);
            }
        } else {
            System.out.println("Nessun elemento trovato per l'autore fornito.");
        }
    }

    private static void ricercaPerTitolo(Scanner scanner, CatalogoDAO catalogoDAO) {
        System.out.print("Inserisci il titolo o parte di esso da cercare: ");
        String titolo = scanner.nextLine();

        List<ElementoCatalogo> elementi = catalogoDAO.ricercaPerTitolo(titolo);

        if (!elementi.isEmpty()) {
            System.out.println("Elementi trovati:");
            for (ElementoCatalogo elemento : elementi) {
                System.out.println(elemento);
            }
        } else {
            System.out.println("Nessun elemento trovato per il titolo fornito.");
        }
    }

    private static void aggiungiUtente(Scanner scanner, UtenteDAO utenteDAO) {
        System.out.println("Inserisci i dettagli del nuovo utente:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Cognome: ");
        String cognome = scanner.nextLine();
        System.out.print("Data di nascita (YYYY-MM-DD): ");
        String dataNascita = scanner.nextLine();

        Utente nuovoUtente = new Utente();
        nuovoUtente.setNome(nome);
        nuovoUtente.setCognome(cognome);
        nuovoUtente.setDataNascita(dataNascita);

        utenteDAO.aggiungiUtente(nuovoUtente);

        System.out.println("Utente aggiunto con successo!");
    }

    private static void rimuoviUtente(Scanner scanner, UtenteDAO utenteDAO) {
        List<Long> userIds = utenteDAO.getAllUserIds();

        if (userIds.isEmpty()) {
            System.out.println("Non ci sono utenti nel database.");
            return;
        }

        System.out.println("Lista degli ID degli utenti:");
        for (Long id : userIds) {
            System.out.println(id);
        }

        System.out.print("Inserisci l'ID dell'utente da rimuovere: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        utenteDAO.rimuoviUtente(id);

        System.out.println("Utente rimosso con successo!");
    }


    private static void ricercaUtentePerId(Scanner scanner, UtenteDAO utenteDAO) {
        System.out.print("Inserisci l'ID dell'utente da cercare: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Utente utente = utenteDAO.ricercaUtentePerId(id);

        if (utente != null) {
            System.out.println("Utente trovato:\n" + utente);
        } else {
            System.out.println("Nessun utente trovato con l'ID fornito.");
        }
    }

    private static void ricercaUtentiPerNome(Scanner scanner, UtenteDAO utenteDAO) {
        System.out.print("Inserisci il nome o parte del nome dell'utente da cercare: ");
        String nome = scanner.nextLine();
        List<Utente> utenti = utenteDAO.ricercaUtentiPerNome(nome);

        if (!utenti.isEmpty()) {
            System.out.println("Utenti trovati:");
            for (Utente utente : utenti) {
                System.out.println(utente);
            }
        } else {
            System.out.println("Nessun utente trovato con il nome fornito.");
        }
    }

    private static void aggiungiPrestito(Scanner scanner, PrestitoDAO prestitoDAO, CatalogoDAO catalogoDAO, UtenteDAO utenteDAO) {
        System.out.println("Inserisci i dettagli del nuovo prestito:");
        List<Long> userIds = utenteDAO.getAllUserIds();
        if (userIds.isEmpty()) {
            System.out.println("Non ci sono utenti nel database. Impossibile effettuare il prestito.");
            return;
        }

        System.out.println("Lista degli ID degli utenti:");
        for (Long id : userIds) {
            System.out.println(id);
        }

        System.out.print("Inserisci l'ID dell'utente: ");
        Long utenteId = scanner.nextLong();
        scanner.nextLine();

        Utente utente = utenteDAO.ricercaUtentePerId(utenteId);
        if (utente == null) {
            System.out.println("Utente non trovato. Impossibile effettuare il prestito.");
            return;
        }

        List<String> isbnList = catalogoDAO.getAllIsbns();
        if (isbnList.isEmpty()) {
            System.out.println("Non ci sono elementi nel catalogo. Impossibile effettuare il prestito.");
            return;
        }

        System.out.println("Lista degli ISBN degli elementi nel catalogo:");
        for (String isbn : isbnList) {
            System.out.println(isbn);
        }

        System.out.print("Inserisci l'ISBN dell'elemento da prestare: ");
        String elementoIsbn = scanner.nextLine();

        ElementoCatalogo catalogElement = catalogoDAO.ricercaPerIsbn(elementoIsbn);
        if (catalogElement == null) {
            System.out.println("Elemento non trovato. Impossibile effettuare il prestito.");
            return;
        }

        System.out.print("Inserisci la data di inizio prestito (YYYY-MM-DD): ");
        String dataInizioPrestito = scanner.nextLine();


        LocalDate dataRestituzionePrevista = LocalDate.parse(dataInizioPrestito).plusDays(30);

        Prestito prestito = new Prestito(null, utente, catalogElement, dataInizioPrestito, dataRestituzionePrevista.toString(), null);

        prestitoDAO.aggiungiPrestito(prestito);

        System.out.println("Prestito aggiunto con successo!");
    }

    private static void rimuoviPrestito(Scanner scanner, PrestitoDAO prestitoDAO, UtenteDAO utenteDAO) {
        List<Long> userIds = utenteDAO.getAllUserIds();
        if (userIds.isEmpty()) {
            System.out.println("Non ci sono utenti nel database.");
            return;
        }

        System.out.println("Lista degli ID degli utenti:");
        for (Long id : userIds) {
            System.out.println(id);
        }

        System.out.print("Inserisci l'ID dell'utente per visualizzare i prestiti in corso: ");
        Long utenteId = scanner.nextLong();
        scanner.nextLine();


        Utente utente = utenteDAO.ricercaUtentePerId(utenteId);
        if (utente == null) {
            System.out.println("Utente non trovato.");
            return;
        }

        List<Prestito> prestiti = prestitoDAO.ricercaPrestitiUtente(utente);

        if (!prestiti.isEmpty()) {
            System.out.println("Prestiti in corso dell'utente " + utente.getNome() + " " + utente.getCognome() + ":");
            for (Prestito prestito : prestiti) {
                System.out.println("ID Prestito: " + prestito.getId() + ", " + prestito);
            }

            System.out.print("Inserisci l'ID del prestito da rimuovere: ");
            Long prestitoId = scanner.nextLong();
            scanner.nextLine();

            prestitoDAO.rimuoviPrestito(prestitoId);

            System.out.println("Prestito rimosso con successo!");
        } else {
            System.out.println("Nessun prestito in corso trovato per l'utente con ID " + utenteId);
        }
    }

    private static void ricercaPrestitoPerId(Scanner scanner, PrestitoDAO prestitoDAO) {
        System.out.print("Inserisci l'ID del prestito da cercare: ");
        Long prestitoId = scanner.nextLong();
        scanner.nextLine();

        Prestito prestito = prestitoDAO.ricercaPrestitoPerId(prestitoId);

        if (prestito != null) {
            System.out.println("Prestito trovato:\n" + prestito);
        } else {
            System.out.println("Nessun prestito trovato con l'ID fornito.");
        }
    }

    private static void ricercaPrestitiUtente(Scanner scanner, PrestitoDAO prestitoDAO, UtenteDAO utenteDAO) {
        List<Long> userIds = utenteDAO.getAllUserIds();

        if (userIds.isEmpty()) {
            System.out.println("Non ci sono utenti nel database.");
            return;
        }

        System.out.println("Lista degli ID degli utenti:");
        for (Long id : userIds) {
            System.out.println(id);
        }

        System.out.print("Inserisci l'ID dell'utente per visualizzare i prestiti: ");
        Long utenteId = scanner.nextLong();
        scanner.nextLine();

        Utente utente = utenteDAO.ricercaUtentePerId(utenteId);
        if (utente == null) {
            System.out.println("Utente non trovato.");
            return;
        }

        List<Prestito> prestiti = prestitoDAO.ricercaPrestitiUtente(utente);

        if (!prestiti.isEmpty()) {
            System.out.println("Prestiti dell'utente " + utente.getNome() + " " + utente.getCognome() + ":");
            for (Prestito prestito : prestiti) {
                System.out.println("ID Prestito: " + prestito.getId() + ", " + prestito);
            }
        } else {
            System.out.println("Nessun prestito trovato per l'utente con ID " + utenteId);
        }
    }

    private static void ricercaPrestitiScadutiNonRestituiti(PrestitoDAO prestitoDAO) {
        List<Prestito> prestitiScaduti = prestitoDAO.ricercaPrestitiScadutiNonRestituiti();

        if (!prestitiScaduti.isEmpty()) {
            System.out.println("Prestiti scaduti e non restituiti:");
            for (Prestito prestito : prestitiScaduti) {
                System.out.println(prestito);
            }
        } else {
            System.out.println("Nessun prestito scaduto e non restituito trovato.");
        }
    }



}
