package m1.w1.d1;

import java.util.Scanner;

public class Esercizio3 {

    public static double perimetroRettangolo(double lato1, double lato2) {
        return 2 * (lato1 + lato2);
    }

    public static int pariDispari(int numero) {
        return numero % 2 == 0 ? 0 : 1;
    }

    public static double perimetroTriangolo(double lato1, double lato2, double lato3) {
        return lato1 + lato2 + lato3;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Chiedi all'utente di inserire la lunghezza dei lati di un rettangolo
        System.out.print("Inserisci la lunghezza del primo lato del rettangolo: ");
        double rettangoloLato1 = scanner.nextDouble();

        System.out.print("Inserisci la lunghezza del secondo lato del rettangolo: ");
        double rettangoloLato2 = scanner.nextDouble();

        // Calcola il perimetro del rettangolo
        double perimetroRettangolo = perimetroRettangolo(rettangoloLato1, rettangoloLato2);
        System.out.println("Perimetro del rettangolo: " + perimetroRettangolo);

        // Chiedi all'utente di inserire un numero intero
        System.out.print("Inserisci un numero intero: ");
        int numero = scanner.nextInt();

        // Determina se il numero è pari o dispari
        int risultatoPariDispari = pariDispari(numero);
        System.out.println("Risultato Pari/Dispari: " + risultatoPariDispari);

        // Chiedi all'utente di inserire le lunghezze dei lati di un triangolo
        System.out.print("Inserisci la lunghezza del primo lato del triangolo: ");
        double triangoloLato1 = scanner.nextDouble();

        System.out.print("Inserisci la lunghezza del secondo lato del triangolo: ");
        double triangoloLato2 = scanner.nextDouble();

        System.out.print("Inserisci la lunghezza del terzo lato del triangolo: ");
        double triangoloLato3 = scanner.nextDouble();

        // Calcola il perimetro del triangolo usando la formula di Erone
        double perimetroTriangolo = perimetroTriangolo(triangoloLato1, triangoloLato2, triangoloLato3);
        System.out.println("Perimetro del triangolo: " + perimetroTriangolo);

        // Chiudi lo scanner
        scanner.close();
    }
}

