/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.google.gson.Gson;
import com.jcraft.jsch.Session;
import control.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Usuario;
import model.UsuarioCurso;
import model.UsuarioDados;
import org.glassfish.ha.store.criteria.Criteria;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Ton
 */
@ManagedBean(name = "UsuarioCursoC")
@ApplicationScoped

public class UsuarioCursoUtil implements Serializable {

    private EntityManagerFactory emf = null;

    public UsuarioCursoUtil() {
        this.emf = (new Dao()).emf();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public String listaJson() {
        return new Gson().toJson(findUsuarioCursoEntities());
    }

    public String listaJsonPorUsuario(int usuario) {
        return new Gson().toJson(listaPorUsuario(usuario));
    }

    public List<UsuarioDados> listaPorUsuario(int usuario) {
        String query = "SELECT c.nome as nome,c.descricao as descricao,h.dia as dia,h.horario as horario,h.duracao as duracao FROM UsuarioCurso u, Curso c, CursoHorario h WHERE c.cursoId = u.cursoId AND c.cursoId = h.cursoId AND u.usuarioId = :usuario";
//        String query = "UsuarioCurso.dados";
//            String query = "SELECT c.NOME,c.DESCRICAO,h.DIA,h.HORARIO,h.DURACAO"
//                    + " FROM UsuarioCurso u"
//                    + " INNER JOIN (Curso c INNER JOIN CursoHorario h ON (c.cursoId = h.cursoId)) ON (c.cursoId = u.cursoId)"
//                    + " WHERE u.USUARIO_ID = :usuario";
//
        EntityManager em = getEntityManager();

        try {
            org.hibernate.Session session = (org.hibernate.Session) em.getDelegate();
            org.hibernate.Query q = session.createQuery(query);
            q.setParameter("usuario", usuario);
            q.setResultTransformer(Transformers.aliasToBean(UsuarioDados.class));
            return q.list();
        } finally {
            em.close();
        }

//        return getEntityManager()
//                .createNamedQuery("UsuarioCurso.porUsuario")
//                .setParameter("usuario", usuario)
//                .getResultList();
//        try {
//            
//            q.setParameter("usuario", usuario);
//            q.setResultTransformer(Transformers.aliasToBean(UsuarioDados.class));
//            return q.list();
//        } finally {
//            em.close();
//        }
    }

    public void create(UsuarioCurso usuarioCurso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usuarioCurso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UsuarioCurso usuarioCurso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuarioCurso = em.merge(usuarioCurso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarioCurso.getUsuarioCursoId();
                if (findUsuarioCurso(id) == null) {
                    throw new NonexistentEntityException("The usuarioCurso with id " + id + " no longer exists.");
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
            UsuarioCurso usuarioCurso;
            try {
                usuarioCurso = em.getReference(UsuarioCurso.class, id);
                usuarioCurso.getUsuarioCursoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarioCurso with id " + id + " no longer exists.", enfe);
            }
            em.remove(usuarioCurso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UsuarioCurso> findUsuarioCursoEntities() {
        return findUsuarioCursoEntities(true, -1, -1);
    }

    public List<UsuarioCurso> findUsuarioCursoEntities(int maxResults, int firstResult) {
        return findUsuarioCursoEntities(false, maxResults, firstResult);
    }

    private List<UsuarioCurso> findUsuarioCursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UsuarioCurso.class));
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

    public UsuarioCurso findUsuarioCurso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UsuarioCurso.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCursoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UsuarioCurso> rt = cq.from(UsuarioCurso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
