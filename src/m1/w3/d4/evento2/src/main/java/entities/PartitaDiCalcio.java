package entities;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "partita_di_calcio")
public class PartitaDiCalcio extends Evento{
    private String casa;
    private String ospite;
    private String vincitore;

    @Column(name = "goal_casa")


}
