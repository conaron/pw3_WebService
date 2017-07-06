/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ton
 */
@Entity
@Table(name = "curso_horario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CursoHorario.findAll", query = "SELECT c FROM CursoHorario c")
    , @NamedQuery(name = "CursoHorario.findByCursoHorarioId", query = "SELECT c FROM CursoHorario c WHERE c.cursoHorarioId = :cursoHorarioId")
    , @NamedQuery(name = "CursoHorario.findByDia", query = "SELECT c FROM CursoHorario c WHERE c.dia = :dia")
    , @NamedQuery(name = "CursoHorario.findByHorario", query = "SELECT c FROM CursoHorario c WHERE c.horario = :horario")
    , @NamedQuery(name = "CursoHorario.findByDuracao", query = "SELECT c FROM CursoHorario c WHERE c.duracao = :duracao")})
public class CursoHorario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "curso_horario_id")
    private Integer cursoHorarioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "dia")
    private String dia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horario")
    @Temporal(TemporalType.TIME)
    private Date horario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duracao")
    private int duracao;
    @Column(name = "curso_id")
    private int cursoId;

    public CursoHorario() {
    }

    public CursoHorario(Integer cursoHorarioId) {
        this.cursoHorarioId = cursoHorarioId;
    }

    public CursoHorario(Integer cursoHorarioId, String dia, Date horario, int duracao) {
        this.cursoHorarioId = cursoHorarioId;
        this.dia = dia;
        this.horario = horario;
        this.duracao = duracao;
    }

    public Integer getCursoHorarioId() {
        return cursoHorarioId;
    }

    public void setCursoHorarioId(Integer cursoHorarioId) {
        this.cursoHorarioId = cursoHorarioId;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
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
        hash += (cursoHorarioId != null ? cursoHorarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoHorario)) {
            return false;
        }
        CursoHorario other = (CursoHorario) object;
        if ((this.cursoHorarioId == null && other.cursoHorarioId != null) || (this.cursoHorarioId != null && !this.cursoHorarioId.equals(other.cursoHorarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CursoHorario[ cursoHorarioId=" + cursoHorarioId + " ]";
    }

}
