package m1.w2.d1;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalcolaKmPerLitro {
    private static final Logger logger = LoggerFactory.getLogger(CalcolaKmPerLitro.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double kmPercorsi, litriConsumati;

        try {
            // Chiede all'utente di inserire il numero di km percorsi
            System.out.print("Inserisci il numero di km percorsi: ");
            kmPercorsi = scanner.nextDouble();

            // Chiede all'utente di inserire il numero di litri consumati
            System.out.print("Inserisci il numero di litri consumati: ");
            litriConsumati = scanner.nextDouble();

            // Calcola il numero di km per litro
            double kmPerLitro = calcolaKmPerLitro(kmPercorsi, litriConsumati);

            // Stampa il risultato
            logger.info("Km/litro percorsi: {}", kmPerLitro);

        } catch (Exception e) {
            // Gestisce eventuali eccezioni, inclusa la divisione per zero
            logger.error("Errore: {}", e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static double calcolaKmPerLitro(double kmPercorsi, double litriConsumati) {
        if (litriConsumati == 0) {
            throw new ArithmeticException("Impossibile calcolare il numero di km/litro con 0 litri consumati.");
        }

        return kmPercorsi / litriConsumati;
    }
}

