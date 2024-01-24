package dao;

import entities.Evento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EventoDAO {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public EventoDAO() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("GestoreEventiPU");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public EventoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Evento evento) {
        entityManager.getTransaction().begin();
        entityManager.persist(evento);
        entityManager.getTransaction().commit();
    }

    public Evento getById(Long id) {
        return entityManager.find(Evento.class, id);
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Evento e = getById(id);
        entityManager.remove(e);
        entityManager.getTransaction().commit();
    }


    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}

