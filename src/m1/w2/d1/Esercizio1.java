package m1.w2.d1;

import java.util.Random;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Esercizio1 {
    private static final Logger logger = LoggerFactory.getLogger(Esercizio1.class);

    public static void main(String[] args) {
        // Inizializza l'array con valori casuali tra 1 e 10
        int[] array = generateRandomArray(5, 1, 10);

        // Stampa l'array iniziale
        printArray(array);

        // Loop principale
        Scanner scanner = new Scanner(System.in);
        int position, value;

        do {
            try {
                // Chiede all'utente di inserire una posizione e un numero
                System.out.print("Inserisci la posizione (1-5) o 0 per uscire: ");
                position = scanner.nextInt();

                if (position == 0) {
                    // L'utente ha inserito 0, esce dal loop
                    break;
                }

                if (position < 1 || position > 5) {
                    throw new IllegalArgumentException("Posizione non valida. Inserisci un numero tra 1 e 5.");
                }

                System.out.print("Inserisci il nuovo numero: ");
                value = scanner.nextInt();

                // Aggiorna l'array con il nuovo valore alla posizione specificata
                array[position - 1] = value;

                // Stampa il nuovo stato dell'array
                printArray(array);
            } catch (IllegalArgumentException e) {
                logger.error("Errore: {}", e.getMessage());
                scanner.nextLine(); // Consuma il resto della linea per evitare loop infinito
            } catch (Exception e) {
                logger.error("Errore non previsto: {}", e.getMessage());
                scanner.nextLine(); // Consuma il resto della linea per evitare loop infinito
            }
        } while (true);

        logger.info("Programma terminato.");
        scanner.close();
    }

    private static int[] generateRandomArray(int size, int min, int max) {
        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }

        return array;
    }

    private static void printArray(int[] array) {
        System.out.print("Array: ");
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}

