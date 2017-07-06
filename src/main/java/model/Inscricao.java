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
@Table(name = "inscricao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inscricao.findAll", query = "SELECT i FROM Inscricao i")
    , @NamedQuery(name = "Inscricao.findByInscricaoId", query = "SELECT i FROM Inscricao i WHERE i.inscricaoId = :inscricaoId")
    , @NamedQuery(name = "Inscricao.findByNome", query = "SELECT i FROM Inscricao i WHERE i.nome = :nome")
    , @NamedQuery(name = "Inscricao.findByEmail", query = "SELECT i FROM Inscricao i WHERE i.email = :email")
    , @NamedQuery(name = "Inscricao.findByFone", query = "SELECT i FROM Inscricao i WHERE i.fone = :fone")})
public class Inscricao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "inscricao_id")
    private Integer inscricaoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nome")
    private String nome;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "fone")
    private String fone;
    @Column(name = "curso_id")
    private int cursoId;

    public Inscricao() {
    }

    public Inscricao(Integer inscricaoId) {
        this.inscricaoId = inscricaoId;
    }

    public Inscricao(Integer inscricaoId, String nome, String email, String fone) {
        this.inscricaoId = inscricaoId;
        this.nome = nome;
        this.email = email;
        this.fone = fone;
    }

    public Integer getInscricaoId() {
        return inscricaoId;
    }

    public void setInscricaoId(Integer inscricaoId) {
        this.inscricaoId = inscricaoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inscricaoId != null ? inscricaoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inscricao)) {
            return false;
        }
        Inscricao other = (Inscricao) object;
        if ((this.inscricaoId == null && other.inscricaoId != null) || (this.inscricaoId != null && !this.inscricaoId.equals(other.inscricaoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Inscricao[ inscricaoId=" + inscricaoId + " ]";
    }

}
