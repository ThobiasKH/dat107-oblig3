package com.example;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ProsjektDAO {
    private EntityManagerFactory emf;

    public ProsjektDAO() {
        emf = Persistence.createEntityManagerFactory("oblig3PU");
    }
    
    public Prosjekt finnProsjektMedId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Prosjekt.class, id);
        } finally {
            em.close();
        }
    }
    
    public void leggTilProsjekt(Prosjekt p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch(Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public List<Prosjekt> hentAlleProsjekter() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Prosjekt> query = em.createQuery("SELECT p FROM Prosjekt p", Prosjekt.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public void close() {
        if(emf != null && emf.isOpen()){
            emf.close();
        }
    }
}
