/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import control.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Enquete;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

/**
 *
 * @author Ton
 */
@ManagedBean(name = "EnqueteC")
@ApplicationScoped

public class EnqueteUtil implements Serializable {

    private EntityManagerFactory emf = null;

    public EnqueteUtil() {
        this.emf = (new Dao()).emf();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public String listaJson() {
        return new Gson().toJson(findEnquetesUltima());
    }

    public List<Enquete> findEnquetesUltima() {
        EntityManager em = getEntityManager();
        List<Enquete> enquetes = em
                .createNamedQuery("Enquete.findLast")
                .setMaxResults(1)
                .getResultList();
        return enquetes;
    }

    public void create(Enquete enquete) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(enquete);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Enquete enquete) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            enquete = em.merge(enquete);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enquete.getEnqueteId();
                if (findEnquete(id) == null) {
                    throw new NonexistentEntityException("The enquete with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Enquete enquete;
            try {
                enquete = em.getReference(Enquete.class, id);
                enquete.getEnqueteId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enquete with id " + id + " no longer exists.", enfe);
            }
            em.remove(enquete);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Enquete> findEnqueteEntities() {
        return findEnqueteEntities(true, -1, -1);
    }

    public List<Enquete> findEnqueteEntities(int maxResults, int firstResult) {
        return findEnqueteEntities(false, maxResults, firstResult);
    }

    private List<Enquete> findEnqueteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Enquete.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Enquete findEnquete(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Enquete.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnqueteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Enquete> rt = cq.from(Enquete.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
