package m1.w2.d2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GestoreListe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Inserisci un numero intero N: ");
            int N = scanner.nextInt();

            List<Integer> listaCasuale = generaListaCasuale(N);
            System.out.println("Lista casuale: " + listaCasuale);

            List<Integer> nuovaLista = creaNuovaLista(listaCasuale);
            System.out.println("Nuova lista: " + nuovaLista);

            System.out.print("Stampare valori in posizioni pari (true) o dispari (false): ");
            boolean stampaPari = scanner.nextBoolean();
            stampaPosizioni(listaCasuale, stampaPari);
        } catch (Exception e) {
            System.out.println("Errore: inserisci un input valido.");
        } finally {
            scanner.close();
        }
    }

    private static List<Integer> generaListaCasuale(int N) {
        Random random = new Random();
        List<Integer> listaCasuale = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            listaCasuale.add(random.nextInt(101)); // Numeri casuali da 0 a 100
        }

        return listaCasuale;
    }

    private static List<Integer> creaNuovaLista(List<Integer> lista) {
        List<Integer> nuovaLista = new ArrayList<>(lista);
        Collections.reverse(nuovaLista);
        lista.addAll(nuovaLista);
        return lista;
    }

    private static void stampaPosizioni(List<Integer> lista, boolean stampaPari) {
        System.out.print("Valori in posizioni ");
        if (stampaPari) {
            System.out.print("pari: ");
            for (int i = 0; i < lista.size(); i += 2) {
                System.out.print(lista.get(i) + " ");
            }
        } else {
            System.out.print("dispari: ");
            for (int i = 1; i < lista.size(); i += 2) {
                System.out.print(lista.get(i) + " ");
            }
        }
    }
}

