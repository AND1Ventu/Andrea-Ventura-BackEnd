package m1.w1.d2;

import java.util.Scanner;

public class Esercizio2Switch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Chiedi all'utente di inserire un intero
        System.out.print("Inserisci un intero compreso tra 0 e 3: ");
        int numero = scanner.nextInt();

        // Utilizza uno statement switch per stampare il numero in lettere o un messaggio d'errore
        switch (numero) {
            case 0:
                System.out.println("Zero");
                break;
            case 1:
                System.out.println("Uno");
                break;
            case 2:
                System.out.println("Due");
                break;
            case 3:
                System.out.println("Tre");
                break;
            default:
                System.out.println("Errore: Il numero non è compreso tra 0 e 3");
        }

        // Chiudi lo scanner
        scanner.close();
    }
}

