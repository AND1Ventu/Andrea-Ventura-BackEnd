package m1.w1.d2;
import java.util.Scanner;

public class Esercizio1IfElseIf {

    public static boolean stringaPariDispari(String input) {
        int lunghezza = input.length();
        return lunghezza % 2 == 0;
    }

    public static boolean annoBisestile(int anno) {
        return ((anno % 4 == 0) && (anno % 100 != 0)) || (anno % 400 == 0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Chiedi all'utente di inserire una stringa
        System.out.print("Inserisci una stringa: ");
        String inputStringa = scanner.nextLine();

        // Verifica se il numero di caratteri è pari o dispari
        boolean risultatoStringaPariDispari = stringaPariDispari(inputStringa);
        System.out.println("Il numero di caratteri è pari: " + risultatoStringaPariDispari);

        // Chiedi all'utente di inserire un anno
        System.out.print("Inserisci un anno: ");
        int inputAnno = scanner.nextInt();

        // Verifica se l'anno è bisestile
        boolean risultatoAnnoBisestile = annoBisestile(inputAnno);
        System.out.println("L'anno è bisestile: " + risultatoAnnoBisestile);

        // Chiudi lo scanner
        scanner.close();
    }
}

