package com.mycompany.automovil.persistencia;

import com.mycompany.automovil.logica.Automovil;
import com.mycompany.automovil.persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AutomovilJpaController implements Serializable {

    public AutomovilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public AutomovilJpaController() {
        this.emf = Persistence.createEntityManagerFactory("AutoPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Automovil automovil) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(automovil);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void edit(Automovil automovil) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (findAutomovil(automovil.getId()) == null) {
                throw new NonexistentEntityException("El automóvil con ID " + automovil.getId() + " no existe.");
            }
            em.merge(automovil);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Automovil automovil = em.find(Automovil.class, id);
            if (automovil == null) {
                throw new NonexistentEntityException("El automóvil con ID " + id + " no existe.");
            }
            em.remove(automovil);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Automovil> findAutomovilEntities() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Automovil a", Automovil.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Automovil findAutomovil(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Automovil.class, id);
        } finally {
            em.close();
        }
    }
}
