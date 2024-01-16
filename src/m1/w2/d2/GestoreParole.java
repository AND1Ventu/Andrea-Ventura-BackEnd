import java.util.*;

public class GestoreParole {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = leggiNumeroElementi(scanner);

        Set<String> paroleViste = new HashSet<>();
        List<String> paroleInserite = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.print("Inserisci parola #" + (i + 1) + ": ");
            String parola = scanner.next();

            // Verifica se la parola è già stata inserita
            if (!paroleViste.add(parola)) {
                System.out.println("Parola duplicata: " + parola);
            }

            paroleInserite.add(parola);
        }

        System.out.println("\nNumero di parole distinte: " + paroleViste.size());

        System.out.println("\nElenco delle parole distinte:");
        stampaParoleDistinte(paroleViste);

    }

    private static int leggiNumeroElementi(Scanner scanner) {
        int n = 0;
        boolean inputValido = false;

        while (!inputValido) {
            System.out.print("Inserisci il numero di elementi (N): ");

            try {
                n = Integer.parseInt(scanner.next());

                if (n > 0) {
                    inputValido = true;
                } else {
                    System.out.println("Inserisci un numero intero positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Inserisci un numero valido.");
            }
        }

        return n;
    }

    private static void stampaParoleDistinte(Set<String> set) {
        set.forEach(System.out::println);
    }

}
