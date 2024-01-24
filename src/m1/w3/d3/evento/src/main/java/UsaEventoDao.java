import dao.EventoDAO;
import entities.Evento;
import entities.TipoEvento;

import java.time.LocalDate;

public class UsaEventoDao {
    public static void main(String[] args){
        EventoDAO dao = new EventoDAO();

        Evento e = new Evento();

        e.setDescrizione("Concerto I maggio");
        e.setDataEvento(LocalDate.of(2024,05,01));
        e.setTipoEvento(TipoEvento.PUBBLICO);
        e.setTitolo("Concerto I Maggio");
        e.setNumeroMassimoPartecipanti(10000);

        dao.save(e);
        System.out.println(e);

        Evento evento = dao.getById(2L);
        System.out.println(evento);

        dao.delete(2L);

        dao.close();
    }
}
