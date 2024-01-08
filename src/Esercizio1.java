public class Esercizio1 {

    public static int moltiplica(int a, int b) {
        return a * b;
    }

    public static String concatena(String str, int num) {
        return str + num;
    }

    public static String[] inserisciInArray(String[] array, String nuovaStringa) {
        if (array.length != 5) {
            // L'array di input deve avere esattamente 5 elementi
            throw new IllegalArgumentException("L'array deve avere esattamente 5 elementi");
        }

        String[] newArray = new String[6];

        // Copia i primi due elementi nell'array risultante
        System.arraycopy(array, 0, newArray, 0, 2);

        // Inserisce la nuova stringa al terzo posto
        newArray[2] = nuovaStringa;

        // Copia le stringhe precedenti in quarta e quinta posizione rispettivamente in quinta e sesta posizione
        System.arraycopy(array, 2, newArray, 4, 2);

        return newArray;
    }

    public static void main(String[] args) {
        // Invoca i metodi in sequenza
        int risultatoMoltiplicazione = moltiplica(2, 3);
        System.out.println("Risultato Moltiplicazione: " + risultatoMoltiplicazione);

        String risultatoConcatenazione = concatena("Hello", 123);
        System.out.println("Risultato Concatenazione: " + risultatoConcatenazione);

        String[] arrayInput = {"uno", "due", "tre", "quattro", "cinque"};
        String[] risultatoInserimento = inserisciInArray(arrayInput, "nuovaStringa");

        // Stampa l'array risultante dopo l'inserimento
        System.out.print("Risultato Inserimento: [");
        for (String elemento : risultatoInserimento) {
            System.out.print(elemento + ", ");
        }
        System.out.println("]");
    }
}

