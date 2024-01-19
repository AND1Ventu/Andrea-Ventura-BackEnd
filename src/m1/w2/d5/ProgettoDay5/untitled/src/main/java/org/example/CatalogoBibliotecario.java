package org.example;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;

public class CatalogoBibliotecario {
    private static final Logger logger = Logger.getLogger(CatalogoBibliotecario.class.getName());

    private List<ElementoCatalogo> catalogo = new ArrayList<>();

    public void aggiungiElemento(ElementoCatalogo elemento) {
        try {
            catalogo.add(elemento);
            logSuccess("Elemento aggiunto con successo.");
        } catch (Exception e) {
            logError("Errore durante l'aggiunta dell'elemento.", e);
        }
    }

    public void rimuoviElemento(String isbn) {
        try {
            catalogo.removeIf(e -> e.getIsbn().equals(isbn));
            logSuccess("Elemento rimosso con successo.");
        } catch (Exception e) {
            logError("Errore durante la rimozione dell'elemento.", e);
        }
    }

    public ElementoCatalogo ricercaPerIsbn(String isbn) {
        try {
            ElementoCatalogo result = catalogo.stream()
                    .filter(e -> e.getIsbn().equals(isbn))
                    .findFirst()
                    .orElse(null);

            if (result != null) {
                logSuccess("Ricerca per ISBN eseguita con successo. Elemento trovato: " + result.toString());
            } else {
                logSuccess("Ricerca per ISBN eseguita con successo. Nessun elemento trovato.");
            }

            return result;
        } catch (Exception e) {
            logError("Errore durante la ricerca per ISBN.", e);
            return null;
        }
    }

    public List<ElementoCatalogo> ricercaPerAnnoPubblicazione(int anno) {
        try {
            List<ElementoCatalogo> result = catalogo.stream()
                    .filter(e -> e.getAnnoPubblicazione() == anno)
                    .toList();
            logSuccess("Ricerca per anno di pubblicazione eseguita con successo.");
            return result;
        } catch (Exception e) {
            logError("Errore durante la ricerca per anno di pubblicazione.", e);
            return new ArrayList<>();
        }
    }

    public List<ElementoCatalogo> ricercaPerAutore(String autore) {
        try {
            List<ElementoCatalogo> result = catalogo.stream()
                    .filter(e -> e instanceof Libro && ((Libro) e).getAutore().equals(autore))
                    .toList();
            logSuccess("Ricerca per autore eseguita con successo.");
            return result;
        } catch (Exception e) {
            logError("Errore durante la ricerca per autore.", e);
            return new ArrayList<>();
        }
    }

    public static void salvaSuDisco(List<ElementoCatalogo> catalogo, String fileName) {
        try {
            File file = new File(fileName);

            String stringaElementi = catalogo.stream()
                    .map(e -> {
                        if (e instanceof Libro) {
                            Libro libro = (Libro) e;
                            return String.format("L;%s;%s;%d;%d;%s;%s",
                                    libro.getIsbn(), libro.getTitolo(), libro.getAnnoPubblicazione(),
                                    libro.getNumeroPagine(), libro.getAutore(), libro.getGenere());
                        } else if (e instanceof Rivista) {
                            Rivista rivista = (Rivista) e;
                            return String.format("R;%s;%s;%d;%d;%s",
                                    rivista.getIsbn(), rivista.getTitolo(), rivista.getAnnoPubblicazione(),
                                    rivista.getNumeroPagine(), rivista.getPeriodicita());
                        }
                        return "";
                    })
                    .collect(Collectors.joining("\n"));  // Aggiunto \n per separare gli elementi su nuove righe

            FileUtils.writeStringToFile(file, stringaElementi, Charset.defaultCharset(), false);
            logSuccess("Salvataggio su disco eseguito con successo.");
        } catch (IOException e) {
            logError("Errore durante il salvataggio su disco.", e);
        }
    }


    public static List<ElementoCatalogo> caricaDaDisco(String fileName) {
        try {
            File file = new File(fileName);

            String stringaElementi = FileUtils.readFileToString(file, Charset.defaultCharset());
            String[] stringheSingoloElemento = stringaElementi.split("#");

            List<ElementoCatalogo> result = Arrays.stream(stringheSingoloElemento)
                    .map(s -> {
                        String[] elementi = s.split(";");
                        if (elementi.length > 0) {
                            if (elementi[0].equals("L")) {
                                return new Libro(elementi[1], elementi[2], Integer.parseInt(elementi[3]),
                                        Integer.parseInt(elementi[4]), elementi[5], elementi[6]);
                            } else if (elementi[0].equals("R")) {
                                return new Rivista(elementi[1], elementi[2], Integer.parseInt(elementi[3]),
                                        Integer.parseInt(elementi[4]), Rivista.Periodicita.valueOf(elementi[5]));
                            }
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            logSuccess("Caricamento da disco eseguito con successo.");
            return result;
        } catch (IOException e) {
            logError("Errore durante il caricamento da disco.", e);
            return new ArrayList<>();
        }
    }


    private static void logSuccess(String message) {
        logger.info("SUCCESS: " + message);
    }

    private static void logError(String message, Throwable throwable) {
        logger.severe("ERROR: " + message);
        logger.severe("Exception details: " + throwable.getMessage());
    }

    static {
        try {
            FileHandler fileHandler = new FileHandler("catalogo.log");
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ElementoCatalogo> getCatalogo() {
        return catalogo;
    }

}
