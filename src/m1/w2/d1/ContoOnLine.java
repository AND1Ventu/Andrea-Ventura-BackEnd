package m1.w2.d1;

public class ContoOnLine extends ContoCorrente {
    double maxPrelievo;

    public ContoOnLine(String titolare, double saldo, double maxP) {
        super(titolare, saldo);
        this.maxPrelievo = maxP;
    }

    public void stampaSaldo() {
        System.out.println("Titolare: " + titolare + " - Saldo: " + saldo + " - Num movimenti: " + nMovimenti
                + " - Massimo movimenti: " + maxMovimenti + " - Massimo prelievo possibile: " + maxPrelievo);
    }

    @Override
    public void preleva(double x) throws BancaException {
        if (x <= maxPrelievo) {
            super.preleva(x);
        } else {
            throw new BancaException("Il prelievo non è disponibile");
        }
    }
}
