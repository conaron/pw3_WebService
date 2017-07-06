/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ton
 */
@Entity
@Table(name = "usuario_curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioCurso.dados", query = "SELECT c.nome as nome,c.descricao as descricao,h.dia as dia,h.horario as horario,h.duracao as duracao FROM UsuarioCurso u, Curso c, CursoHorario h WHERE c.cursoId = u.cursoId AND c.cursoId = h.cursoId AND u.usuarioId = :usuario")
    , @NamedQuery(name = "UsuarioCurso.findAll", query = "SELECT u FROM UsuarioCurso u")
    , @NamedQuery(name = "UsuarioCurso.porId", query = "SELECT u FROM UsuarioCurso u WHERE u.usuarioCursoId = :usuarioCursoId")
    , @NamedQuery(name = "UsuarioCurso.porUsuario", query = "SELECT u FROM UsuarioCurso u WHERE u.usuarioId = :usuario")})
public class UsuarioCurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usuario_curso_id")
    private Integer usuarioCursoId;
    @Column(name = "curso_id")
    private int cursoId;
    @Column(name = "usuario_id")
    private int usuarioId;

    public UsuarioCurso() {
    }

    public UsuarioCurso(Integer usuarioCursoId) {
        this.usuarioCursoId = usuarioCursoId;
    }

    public Integer getUsuarioCursoId() {
        return usuarioCursoId;
    }

    public void setUsuarioCursoId(Integer usuarioCursoId) {
        this.usuarioCursoId = usuarioCursoId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioCursoId != null ? usuarioCursoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioCurso)) {
            return false;
        }
        UsuarioCurso other = (UsuarioCurso) object;
        if ((this.usuarioCursoId == null && other.usuarioCursoId != null) || (this.usuarioCursoId != null && !this.usuarioCursoId.equals(other.usuarioCursoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.UsuarioCurso[ usuarioCursoId=" + usuarioCursoId + " ]";
    }
    
}
