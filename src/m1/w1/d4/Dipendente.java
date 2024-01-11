public class Dipendente {

    private static final double stipendioBase = 1000.0;

    private int matricola;
    private double stipendio;
    private double importoOrarioStraordinario;
    private Livello livello;
    private Dipartimento dipartimento;

    public enum Livello {
        OPERAIO,
        IMPIEGATO,
        QUADRO,
        DIRIGENTE
    }

    public enum Dipartimento {
        PRODUZIONE,
        AMMINISTRAZIONE,
        VENDITE
    }

    // Costruttore per matricola e Dipartimento
    public Dipendente(int matricola, Dipartimento dipartimento) {
        this.matricola = matricola;
        this.dipartimento = dipartimento;
        this.stipendio = stipendioBase;
        this.importoOrarioStraordinario = 30;
        this.livello = Livello.OPERAIO;
    }

    // Costruttore con tutti i parametri
    public Dipendente(int matricola, double importoOrarioStraordinario, Livello livello, Dipartimento dipartimento) {
        this.matricola = matricola;
        this.importoOrarioStraordinario = importoOrarioStraordinario;
        this.livello = livello;
        this.dipartimento = dipartimento;
        calcolaStipendio();
    }

    public void setImportoOrarioStraordinario(double importoOrarioStraordinario) {
        this.importoOrarioStraordinario = importoOrarioStraordinario;
    }

    public void setDipartimento(Dipartimento dipartimento) {
        this.dipartimento = dipartimento;
    }

    public void stampaDatiDipendente() {
        System.out.println("Matricola: " + matricola);
        System.out.println("Stipendio: " + stipendio);
        System.out.println("Importo Orario Straordinario: " + importoOrarioStraordinario);
        System.out.println("Livello: " + livello);
        System.out.println("Dipartimento: " + dipartimento);
    }

    public Livello promuovi() {
        switch (livello) {
            case OPERAIO:
                livello = Livello.IMPIEGATO;
                break;
            case IMPIEGATO:
                livello = Livello.QUADRO;
                break;
            case QUADRO:
                livello = Livello.DIRIGENTE;
                break;
            case DIRIGENTE:
                System.out.println("Errore: Il dipendente è già un dirigente.");
                break;
        }

        calcolaStipendio(); // Aggiorna lo stipendio dopo la promozione
        return livello;
    }

    private void calcolaStipendio() {
        switch (livello) {
            case IMPIEGATO:
                stipendio = stipendioBase * 1.2;
                break;
            case QUADRO:
                stipendio = stipendioBase * 1.5;
                break;
            case DIRIGENTE:
                stipendio = stipendioBase * 2;
                break;
            default:
                stipendio = stipendioBase;
        }
    }

    public static double calcolaPaga(Dipendente dipendente) {
        return dipendente.stipendio;
    }

    public static double calcolaPaga(Dipendente dipendente, int oreStraordinario) {
        return dipendente.stipendio + (dipendente.importoOrarioStraordinario * oreStraordinario);
    }
}


public class GestioneDipendenti {

    public static void main(String[] args) {
        // Istanzia 4 dipendenti
        Dipendente operaio1 = new Dipendente(1, Dipendente.Dipartimento.PRODUZIONE);
        Dipendente operaio2 = new Dipendente(2, Dipendente.Dipartimento.PRODUZIONE);
        Dipendente impiegato = new Dipendente(3, Dipendente.Dipartimento.AMMINISTRAZIONE);
        Dipendente dirigente = new Dipendente(4, Dipendente.Dipartimento.VENDITE);

        // Stampa lo stato iniziale dei dipendenti
        System.out.println("Stato iniziale dei dipendenti:");
        operaio1.stampaDatiDipendente();
        operaio2.stampaDatiDipendente();
        impiegato.stampaDatiDipendente();
        dirigente.stampaDatiDipendente();

        // Promuovi un operaio a impiegato e l'impiegato a quadro
        System.out.println("\nPromozioni:");
        operaio1.promuovi();
        impiegato.promuovi();

        // Stampa lo stato dopo le promozioni
        System.out.println("\nStato dopo le promozioni:");
        operaio1.stampaDatiDipendente();
        operaio2.stampaDatiDipendente();
        impiegato.stampaDatiDipendente();
        dirigente.stampaDatiDipendente();

        // Calcola la somma degli stipendi con 5 ore di straordinario per ciascun dipendente
        int oreStraordinario = 5;
        double sommaStipendi = Dipendente.calcolaPaga(operaio1, oreStraordinario)
                + Dipendente.calcolaPaga(operaio2, oreStraordinario)
                + Dipendente.calcolaPaga(impiegato, oreStraordinario)
                + Dipendente.calcolaPaga(dirigente, oreStraordinario);

        // Stampa l'importo totale degli stipendi
        System.out.println("\nSomma degli stipendi con 5 ore di straordinario per ciascun dipendente: " + sommaStipendi);
    }
}
