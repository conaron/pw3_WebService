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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ton
 */
@Entity
@Table(name = "curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c")
    , @NamedQuery(name = "Curso.findByCursoId", query = "SELECT c FROM Curso c WHERE c.cursoId = :cursoId")
    , @NamedQuery(name = "Curso.findByNome", query = "SELECT c FROM Curso c WHERE c.nome = :nome")
    , @NamedQuery(name = "Curso.findByDepartamento", query = "SELECT c FROM Curso c WHERE c.curso_departamento_id = :departamento")
    , @NamedQuery(name = "Curso.findByDescricao", query = "SELECT c FROM Curso c WHERE c.descricao = :descricao")})
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "curso_id")
    private Integer cursoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nome")
    private String nome;
    @Size(max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "curso_departamento_id")
    private int curso_departamento_id;

    public Curso() {
    }

    public Curso(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public Curso(Integer cursoId, String nome) {
        this.cursoId = cursoId;
        this.nome = nome;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCurso_departamento_id() {
        return curso_departamento_id;
    }

    public void setCurso_departamento_id(int curso_departamento_id) {
        this.curso_departamento_id = curso_departamento_id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cursoId != null ? cursoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.cursoId == null && other.cursoId != null) || (this.cursoId != null && !this.cursoId.equals(other.cursoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Curso[ cursoId=" + cursoId + " ]";
    }

}
