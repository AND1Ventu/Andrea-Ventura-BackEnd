package m1.w1.d2;

import java.util.Scanner;

public class Esercizio3While {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputStringa;

        // Continua a chiedere all'utente di inserire una stringa fino a che non inserisce ":q"
        while (true) {
            System.out.print("Inserisci una stringa (o ':q' per uscire): ");
            inputStringa = scanner.nextLine();

            // Verifica se l'utente ha inserito ":q" per uscire dal ciclo
            if (":q".equals(inputStringa)) {
                break;
            }

            // Suddivide la stringa in caratteri separati dalla virgola e stampa il risultato
            char[] caratteri = inputStringa.toCharArray();
            for (int i = 0; i < caratteri.length; i++) {
                System.out.print(caratteri[i]);
                // Aggiungi una virgola dopo ogni carattere, tranne per l'ultimo
                if (i < caratteri.length - 1) {
                    System.out.print(",");
                }
            }
            System.out.println(); // Vai a capo dopo aver stampato la stringa suddivisa
        }

        // Chiudi lo scanner
        scanner.close();
    }
}

