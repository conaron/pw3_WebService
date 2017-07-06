/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.google.gson.Gson;
import control.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Curso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.CursoDepartamento;

/**
 *
 * @author Ton
 */
@ManagedBean(name = "CursoDepartamentoC")
@ApplicationScoped

public class CursoDepartamentoUtil implements Serializable {

    private EntityManagerFactory emf = null;

    public CursoDepartamentoUtil() {
        this.emf = (new Dao()).emf();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public String listaJson() {
        return new Gson().toJson(findCursoDepartamentoEntities());
    }

    public void create(CursoDepartamento cursoDepartamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cursoDepartamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CursoDepartamento cursoDepartamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cursoDepartamento = em.merge(cursoDepartamento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cursoDepartamento.getCursoDepartamentoId();
                if (findCursoDepartamento(id) == null) {
                    throw new NonexistentEntityException("The cursoDepartamento with id " + id + " no longer exists.");
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
            CursoDepartamento cursoDepartamento;
            try {
                cursoDepartamento = em.getReference(CursoDepartamento.class, id);
                cursoDepartamento.getCursoDepartamentoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cursoDepartamento with id " + id + " no longer exists.", enfe);
            }
            em.remove(cursoDepartamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CursoDepartamento> findCursoDepartamentoEntities() {
        return findCursoDepartamentoEntities(true, -1, -1);
    }

    public List<CursoDepartamento> findCursoDepartamentoEntities(int maxResults, int firstResult) {
        return findCursoDepartamentoEntities(false, maxResults, firstResult);
    }

    private List<CursoDepartamento> findCursoDepartamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CursoDepartamento.class));
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

    public CursoDepartamento findCursoDepartamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CursoDepartamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursoDepartamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CursoDepartamento> rt = cq.from(CursoDepartamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
