package dao;

import entities.Partecipazione;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class PartecipazioneDAO {

    private final EntityManager entityManager;

    public PartecipazioneDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Partecipazione partecipazione) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(partecipazione);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Partecipazione getById(Long id) {
        return entityManager.find(Partecipazione.class, id);
    }

    public List<Partecipazione> getAll() {
        TypedQuery<Partecipazione> query = entityManager.createQuery("SELECT p FROM Partecipazione p", Partecipazione.class);
        return query.getResultList();
    }

    public void update(Partecipazione partecipazione) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(partecipazione);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Partecipazione partecipazione = entityManager.find(Partecipazione.class, id);
            if (partecipazione != null) {
                entityManager.remove(partecipazione);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
