/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.google.gson.Gson;
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
import model.Curso;
import model.CursoHorario;

/**
 *
 * @author Ton
 */
@ManagedBean(name = "CursoHorarioC")
@ApplicationScoped
public class CursoHorarioUtil implements Serializable {

    private EntityManagerFactory emf = null;

    public CursoHorarioUtil() {
        this.emf = (new Dao()).emf();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public String listaJson() {
        return new Gson().toJson(findCursoHorarioEntities());
    }

    public void create(CursoHorario cursoHorario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cursoHorario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CursoHorario cursoHorario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cursoHorario = em.merge(cursoHorario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cursoHorario.getCursoHorarioId();
                if (findCursoHorario(id) == null) {
                    throw new NonexistentEntityException("The cursoHorario with id " + id + " no longer exists.");
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
            CursoHorario cursoHorario;
            try {
                cursoHorario = em.getReference(CursoHorario.class, id);
                cursoHorario.getCursoHorarioId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cursoHorario with id " + id + " no longer exists.", enfe);
            }
            em.remove(cursoHorario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CursoHorario> findCursoHorarioEntities() {
        return findCursoHorarioEntities(true, -1, -1);
    }

    public List<CursoHorario> findCursoHorarioEntities(int maxResults, int firstResult) {
        return findCursoHorarioEntities(false, maxResults, firstResult);
    }

    private List<CursoHorario> findCursoHorarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CursoHorario.class));
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

    public CursoHorario findCursoHorario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CursoHorario.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursoHorarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CursoHorario> rt = cq.from(CursoHorario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
