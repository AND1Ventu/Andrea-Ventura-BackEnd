import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;


import entities.*;
import dao.PersonaDAO;
import dao.PartecipazioneDAO;
import dao.EventoDAO;
import dao.LocationDAO;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GestioneEventiPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            PersonaDAO personaDAO = new PersonaDAO(entityManager);
            LocationDAO locationDAO = new LocationDAO(entityManager);
            EventoDAO eventoDAO = new EventoDAO(entityManager);
            PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO(entityManager);

            // Creare e salvare una persona
            Persona persona = new Persona("Nome", "Cognome", "email@example.com", LocalDate.now(), Sesso.M);
            personaDAO.save(persona);

            // Creare e salvare una location
            Location location = new Location("Nome Location", "Citta Location");
            locationDAO.save(location);

            // Creare un evento e aggiungere persona e location
            Evento evento = new Evento("Titolo Evento", LocalDate.now(), "Descrizione Evento", TipoEvento.PUBBLICO, 100);
            evento.setLocation(location);
            evento.addPartecipazione(new Partecipazione(persona, evento, StatoPartecipazione.CONFERMATA));

            // Salvare l'evento
            eventoDAO.save(evento);

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}

