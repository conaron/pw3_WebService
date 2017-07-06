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
import model.Noticia;

/**
 *
 * @author Ton
 */
@ManagedBean(name = "NoticiaC")
@ApplicationScoped
public class NoticiaUtil implements Serializable {

    private EntityManagerFactory emf = null;

    public NoticiaUtil() {
        this.emf = (new Dao()).emf();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public String listaJson() {
        return new Gson().toJson(findNoticiaEntities());
    }

    public void create(Noticia noticia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(noticia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Noticia noticia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            noticia = em.merge(noticia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = noticia.getNoticiaId();
                if (findNoticia(id) == null) {
                    throw new NonexistentEntityException("The noticia with id " + id + " no longer exists.");
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
            Noticia noticia;
            try {
                noticia = em.getReference(Noticia.class, id);
                noticia.getNoticiaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The noticia with id " + id + " no longer exists.", enfe);
            }
            em.remove(noticia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Noticia> findNoticiaEntities() {
        return findNoticiaEntities(true, -1, -1);
    }

    public List<Noticia> findNoticiaEntities(int maxResults, int firstResult) {
        return findNoticiaEntities(false, maxResults, firstResult);
    }

    private List<Noticia> findNoticiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Noticia.class));
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

    public Noticia findNoticia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Noticia.class, id);
        } finally {
            em.close();
        }
    }

    public int getNoticiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Noticia> rt = cq.from(Noticia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
