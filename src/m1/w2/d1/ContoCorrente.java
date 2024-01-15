package m1.w2.d1;

public class ContoCorrente {
    String titolare;
    int nMovimenti;
    final int maxMovimenti = 50;
    double saldo;

    public ContoCorrente(String titolare, double saldo) {
        this.titolare = titolare;
        this.saldo = saldo;
        nMovimenti = 0;
    }

    public void preleva(double x) throws BancaException {
        if (nMovimenti < maxMovimenti) {
            saldo = saldo - x;

            if (saldo < 0) {
                throw new BancaException("Il conto è in rosso");
            }
        } else {
            saldo = saldo - x - 0.50;
        }

        nMovimenti++;
    }

    public double restituisciSaldo() {
        return saldo;
    }
}

