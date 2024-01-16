package m1.w2.d2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RubricaTelefonica {

    private Map<String, String> rubrica;

    public RubricaTelefonica() {
        this.rubrica = new HashMap<>();
    }

    public void inserisciContatto(String nome, String telefono) {
        if (nome != null && telefono != null && !nome.trim().isEmpty() && !telefono.trim().isEmpty()) {
            rubrica.put(nome, telefono);
            System.out.println("Contatto inserito con successo.");
        } else {
            System.out.println("Errore: Nome e telefono non possono essere vuoti.");
        }
    }

    public void cancellaContattoPerNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            if (rubrica.containsKey(nome)) {
                rubrica.remove(nome);
                System.out.println("Contatto cancellato con successo.");
            } else {
                System.out.println("Errore: Contatto non trovato.");
            }
        } else {
            System.out.println("Errore: Nome non può essere vuoto.");
        }
    }

    public void ricercaPersonaPerTelefono(String telefono) {
        if (telefono != null && !telefono.trim().isEmpty()) {
            for (Map.Entry<String, String> entry : rubrica.entrySet()) {
                if (entry.getValue().equals(telefono)) {
                    System.out.println("Persona trovata: " + entry.getKey());
                    return;
                }
            }
            System.out.println("Errore: Nessuna persona trovata con questo numero di telefono.");
        } else {
            System.out.println("Errore: Numero di telefono non può essere vuoto.");
        }
    }

    public void ricercaTelefonoPerNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            if (rubrica.containsKey(nome)) {
                System.out.println("Numero di telefono di " + nome + ": " + rubrica.get(nome));
            } else {
                System.out.println("Errore: Nessun contatto trovato con questo nome.");
            }
        } else {
            System.out.println("Errore: Nome non può essere vuoto.");
        }
    }

    public void stampaRubrica() {
        System.out.println("Rubrica Telefonica:");
        for (Map.Entry<String, String> entry : rubrica.entrySet()) {
            System.out.println("Nome: " + entry.getKey() + ", Telefono: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RubricaTelefonica rubricaTelefonica = new RubricaTelefonica();

        try {
            rubricaTelefonica.inserisciContatto("Mario", "123456789");
            rubricaTelefonica.inserisciContatto("Luigi", "987654321");
            rubricaTelefonica.stampaRubrica();

            rubricaTelefonica.cancellaContattoPerNome("Mario");
            rubricaTelefonica.stampaRubrica();

            rubricaTelefonica.ricercaPersonaPerTelefono("987654321");

            rubricaTelefonica.ricercaTelefonoPerNome("Luigi");
        } catch (Exception e) {
            System.out.println("Errore: Si è verificato un problema.");
        } finally {
            scanner.close();
        }
    }
}

