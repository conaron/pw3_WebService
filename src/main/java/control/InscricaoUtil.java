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
import model.Inscricao;

/**
 *
 * @author Ton
 */
@ManagedBean(name = "InscricaoC")
@ApplicationScoped

public class InscricaoUtil implements Serializable {

    private EntityManagerFactory emf = null;

    public InscricaoUtil() {
        this.emf = (new Dao()).emf();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public String listaJson() {
        return new Gson().toJson(findInscricaoEntities());
    }

    public String create(Inscricao inscricao) {
        String retorno = "Erro ao inserir";
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(inscricao);
            em.getTransaction().commit();
            retorno = "Registro inserido";
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return retorno;
    }

    public void edit(Inscricao inscricao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            inscricao = em.merge(inscricao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = inscricao.getInscricaoId();
                if (findInscricao(id) == null) {
                    throw new NonexistentEntityException("The inscricao with id " + id + " no longer exists.");
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
            Inscricao inscricao;
            try {
                inscricao = em.getReference(Inscricao.class, id);
                inscricao.getInscricaoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inscricao with id " + id + " no longer exists.", enfe);
            }
            em.remove(inscricao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Inscricao> findInscricaoEntities() {
        return findInscricaoEntities(true, -1, -1);
    }

    public List<Inscricao> findInscricaoEntities(int maxResults, int firstResult) {
        return findInscricaoEntities(false, maxResults, firstResult);
    }

    private List<Inscricao> findInscricaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Inscricao.class));
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

    public Inscricao findInscricao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Inscricao.class, id);
        } finally {
            em.close();
        }
    }

    public int getInscricaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Inscricao> rt = cq.from(Inscricao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
