package com.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProsjektdeltagelseDAO {
    private EntityManagerFactory emf;

    public ProsjektdeltagelseDAO() {
        emf = Persistence.createEntityManagerFactory("oblig3PU");
    }
    
    public void registrerProsjektdeltagelse(Prosjektdeltagelse pd) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pd);
            em.getTransaction().commit();
        } catch(Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public boolean oppdaterTimerForDeltagelse(int pdId, int nyeTimer) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Prosjektdeltagelse pd = em.find(Prosjektdeltagelse.class, pdId);
            if(pd == null) {
                return false;
            }
            pd.setTimer(nyeTimer);
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
    
    public void close(){
        if(emf != null && emf.isOpen()){
            emf.close();
        }
    }
}
