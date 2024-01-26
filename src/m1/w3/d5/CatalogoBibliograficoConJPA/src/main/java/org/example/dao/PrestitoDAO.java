package org.example.dao;

import org.example.entities.ElementoCatalogo;
import org.example.entities.Prestito;
import org.example.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class PrestitoDAO {

    private EntityManagerFactory emf;

    public PrestitoDAO() {
        this.emf = Persistence.createEntityManagerFactory("catalogo_bibliotecario");
    }

    public void aggiungiPrestito(Prestito prestito) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(prestito);
        em.getTransaction().commit();
        em.close();
    }

    public void rimuoviPrestito(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Prestito prestito = em.find(Prestito.class, id);
        if (prestito != null) {
            em.remove(prestito);
        }
        em.getTransaction().commit();
        em.close();
    }

    public Prestito ricercaPrestitoPerId(Long id) {
        EntityManager em = emf.createEntityManager();
        Prestito prestito = em.find(Prestito.class, id);
        em.close();
        return prestito;
    }

    public List<Prestito> ricercaPrestitiUtente(Utente utente) {
        EntityManager em = emf.createEntityManager();
        List<Prestito> result = em.createQuery("SELECT p FROM Prestito p WHERE p.utente = :utente", Prestito.class)
                .setParameter("utente", utente)
                .getResultList();
        em.close();
        return result;
    }

    public List<Prestito> ricercaPrestitiScadutiNonRestituiti() {
        EntityManager em = emf.createEntityManager();
        List<Prestito> result = em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < :oggi AND p.dataRestituzioneEffettiva IS NULL", Prestito.class)
                .setParameter("oggi", LocalDate.now())
                .getResultList();
        em.close();
        return result;
    }

}

