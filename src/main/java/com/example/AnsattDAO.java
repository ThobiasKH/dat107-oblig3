package com.example;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AnsattDAO {
    private EntityManagerFactory emf;

    public AnsattDAO() {
        emf = Persistence.createEntityManagerFactory("oblig3PU");
    }
    
    public Ansatt finnAnsattMedId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Ansatt.class, id);
        } finally {
            em.close();
        }
    }
    
    public Ansatt finnAnsattMedBrukernavn(String brukernavn) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Ansatt> query = em.createQuery(
                "SELECT a FROM Ansatt a WHERE a.brukernavn = :brukernavn", Ansatt.class);
            query.setParameter("brukernavn", brukernavn);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public List<Ansatt> hentAlleAnsatte() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a", Ansatt.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public void oppdaterAnsatt(int id, String nyStilling, Double nyLonn) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Ansatt ansatt = em.find(Ansatt.class, id);
            if (ansatt != null) {
                ansatt.setStilling(nyStilling);
                ansatt.setLonn(nyLonn);
            }
            em.getTransaction().commit();
        } catch(Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public void leggTilAnsatt(Ansatt nyAnsatt) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(nyAnsatt);
            em.getTransaction().commit();
        } catch(Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public boolean oppdaterAnsattAvdeling(int ansattId, int nyAvdelingId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Ansatt ansatt = em.find(Ansatt.class, ansattId);
            if(ansatt == null) {
                return false;
            }
            TypedQuery<Avdeling> query = em.createQuery("SELECT a FROM Avdeling a WHERE a.sjef.id = :ansattId", Avdeling.class);
            query.setParameter("ansattId", ansattId);
            List<Avdeling> result = query.getResultList();
            if(!result.isEmpty()) {
                System.out.println("Ansatt er sjef og kan derfor ikke bytte avdeling.");
                return false;
            }
            Avdeling nyAvdeling = em.find(Avdeling.class, nyAvdelingId);
            if(nyAvdeling == null) {
                System.out.println("Finner ikke den oppgitte avdelingen.");
                return false;
            }
            ansatt.setAvdeling(nyAvdeling);
            em.getTransaction().commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }
    
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}