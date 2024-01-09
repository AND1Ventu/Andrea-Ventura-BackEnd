package m1.w1.d1;

import java.util.Scanner;

public class Esercizio2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Chiedi all'utente di inserire tre stringhe
        System.out.print("Inserisci la prima stringa: ");
        String stringa1 = scanner.nextLine();

        System.out.print("Inserisci la seconda stringa: ");
        String stringa2 = scanner.nextLine();

        System.out.print("Inserisci la terza stringa: ");
        String stringa3 = scanner.nextLine();

        // Concatena le stringhe in ordine di inserimento
        String concatenazioneInOrdine = stringa1 + stringa2 + stringa3;

        // Concatena le stringhe in ordine di inserimento inverso
        String concatenazioneInOrdineInverso = stringa3 + stringa2 + stringa1;

        // Stampa i risultati
        System.out.println("Concatenazione in ordine di inserimento: " + concatenazioneInOrdine);
        System.out.println("Concatenazione in ordine di inserimento inverso: " + concatenazioneInOrdineInverso);

        // Chiudi lo scanner
        scanner.close();
    }
}
