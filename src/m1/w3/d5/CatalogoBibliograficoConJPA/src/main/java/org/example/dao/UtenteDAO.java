package org.example.dao;

import org.example.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class UtenteDAO {

    private EntityManagerFactory emf;

    public UtenteDAO() {
        this.emf = Persistence.createEntityManagerFactory("catalogo_bibliotecario");

    }

    public void aggiungiUtente(Utente utente) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(utente);
        em.getTransaction().commit();
        em.close();
    }

    public void rimuoviUtente(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Utente utente = em.find(Utente.class, id);
        if (utente != null) {
            em.remove(utente);
        }
        em.getTransaction().commit();
        em.close();
    }

    public Utente ricercaUtentePerId(Long id) {
        EntityManager em = emf.createEntityManager();
        Utente utente = em.find(Utente.class, id);
        em.close();
        return utente;
    }

    public List<Utente> ricercaUtentiPerNome(String nome) {
        EntityManager em = emf.createEntityManager();
        List<Utente> result = em.createQuery("SELECT u FROM Utente u WHERE u.nome = :nome", Utente.class)
                .setParameter("nome", nome)
                .getResultList();
        em.close();
        return result;
    }

    public List<Long> getAllUserIds() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT u.id FROM Utente u");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
