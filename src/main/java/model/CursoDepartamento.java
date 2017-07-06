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
@Table(name = "curso_departamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CursoDepartamento.findAll", query = "SELECT c FROM CursoDepartamento c")
    , @NamedQuery(name = "CursoDepartamento.findByCursoDepartamentoId", query = "SELECT c FROM CursoDepartamento c WHERE c.cursoDepartamentoId = :cursoDepartamentoId")
    , @NamedQuery(name = "CursoDepartamento.findByNome", query = "SELECT c FROM CursoDepartamento c WHERE c.nome = :nome")})
public class CursoDepartamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "curso_departamento_id")
    private Integer cursoDepartamentoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nome")
    private String nome;

    public CursoDepartamento() {
    }

    public CursoDepartamento(Integer cursoDepartamentoId) {
        this.cursoDepartamentoId = cursoDepartamentoId;
    }

    public CursoDepartamento(Integer cursoDepartamentoId, String nome) {
        this.cursoDepartamentoId = cursoDepartamentoId;
        this.nome = nome;
    }

    public Integer getCursoDepartamentoId() {
        return cursoDepartamentoId;
    }

    public void setCursoDepartamentoId(Integer cursoDepartamentoId) {
        this.cursoDepartamentoId = cursoDepartamentoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cursoDepartamentoId != null ? cursoDepartamentoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoDepartamento)) {
            return false;
        }
        CursoDepartamento other = (CursoDepartamento) object;
        if ((this.cursoDepartamentoId == null && other.cursoDepartamentoId != null) || (this.cursoDepartamentoId != null && !this.cursoDepartamentoId.equals(other.cursoDepartamentoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CursoDepartamento[ cursoDepartamentoId=" + cursoDepartamentoId + " ]";
    }
    
}
