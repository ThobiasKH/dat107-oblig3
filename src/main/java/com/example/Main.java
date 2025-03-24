package com.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("oblig3PU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Ansatt ansatt = em.find(Ansatt.class, 1);  
            if (ansatt != null) {
                ansatt.printAnsattDetaljer();
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
