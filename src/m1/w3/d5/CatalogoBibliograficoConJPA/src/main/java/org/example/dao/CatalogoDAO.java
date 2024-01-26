package org.example.dao;

import org.example.entities.ElementoCatalogo;
import org.example.entities.Prestito;
import org.example.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class CatalogoDAO {

    private EntityManagerFactory emf;

    public CatalogoDAO() {
        this.emf = Persistence.createEntityManagerFactory("catalogo_bibliotecario");
    }

    public void aggiungiElemento(ElementoCatalogo elemento) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(elemento);
        em.getTransaction().commit();
        em.close();
    }

    public void rimuoviElemento(String isbn) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ElementoCatalogo elemento = em.find(ElementoCatalogo.class, isbn);
        if (elemento != null) {
            em.remove(elemento);
        }
        em.getTransaction().commit();
        em.close();
    }

    public ElementoCatalogo ricercaPerIsbn(String isbn) {
        EntityManager em = emf.createEntityManager();
        ElementoCatalogo elemento = em.find(ElementoCatalogo.class, isbn);
        em.close();
        return elemento;
    }

    public List<ElementoCatalogo> ricercaPerAnnoPubblicazione(int anno) {
        EntityManager em = emf.createEntityManager();
        List<ElementoCatalogo> result = em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :anno", ElementoCatalogo.class)
                .setParameter("anno", anno)
                .getResultList();
        em.close();
        return result;
    }

    public List<ElementoCatalogo> ricercaPerAutore(String autore) {
        EntityManager em = emf.createEntityManager();
        List<ElementoCatalogo> result = em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.autore = :autore", ElementoCatalogo.class)
                .setParameter("autore", autore)
                .getResultList();
        em.close();
        return result;
    }

    public List<ElementoCatalogo> ricercaPerTitolo(String titolo) {
        EntityManager em = emf.createEntityManager();
        List<ElementoCatalogo> result = em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.titolo LIKE :titolo", ElementoCatalogo.class)
                .setParameter("titolo", "%" + titolo + "%")
                .getResultList();
        em.close();
        return result;
    }

    public List<String> getAllIsbns() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT e.isbn FROM ElementoCatalogo e");
            return query.getResultList();
        } finally {
            em.close();
        }
    }


}
