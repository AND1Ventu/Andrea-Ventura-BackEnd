package m1.w1.d2;

import java.util.Scanner;

public class Esercizio4For {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Chiedi all'utente di inserire un numero intero
        System.out.print("Inserisci un numero intero: ");

        // Verifica se l'input è un numero intero
        if (scanner.hasNextInt()) {
            int numero = scanner.nextInt();

            // Stampa il conto alla rovescia da numero fino a zero
            System.out.println("Conto alla rovescia:");

            for (int i = numero; i >= 0; i--) {
                System.out.println(i);
            }
        } else {
            System.out.println("Errore: L'input non è un numero intero.");
        }

        // Chiudi lo scanner
        scanner.close();
    }
}

