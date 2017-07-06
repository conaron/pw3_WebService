/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "enquete")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enquete.findAll", query = "SELECT e FROM Enquete e")
    , @NamedQuery(name = "Enquete.findLast", query = "SELECT e FROM Enquete e ORDER BY e.enqueteId DESC")
    , @NamedQuery(name = "Enquete.findByEnqueteId", query = "SELECT e FROM Enquete e WHERE e.enqueteId = :enqueteId")
    , @NamedQuery(name = "Enquete.findByData", query = "SELECT e FROM Enquete e WHERE e.data = :data")
    , @NamedQuery(name = "Enquete.findByPergunta", query = "SELECT e FROM Enquete e WHERE e.pergunta = :pergunta")
    , @NamedQuery(name = "Enquete.findByOpcao1", query = "SELECT e FROM Enquete e WHERE e.opcao1 = :opcao1")
    , @NamedQuery(name = "Enquete.findByOpcao2", query = "SELECT e FROM Enquete e WHERE e.opcao2 = :opcao2")
    , @NamedQuery(name = "Enquete.findByOpcao3", query = "SELECT e FROM Enquete e WHERE e.opcao3 = :opcao3")
    , @NamedQuery(name = "Enquete.findByOpcao1Quantidade", query = "SELECT e FROM Enquete e WHERE e.opcao1Quantidade = :opcao1Quantidade")
    , @NamedQuery(name = "Enquete.findByOpcao2Quantidade", query = "SELECT e FROM Enquete e WHERE e.opcao2Quantidade = :opcao2Quantidade")
    , @NamedQuery(name = "Enquete.findByOpcao3Quantidade", query = "SELECT e FROM Enquete e WHERE e.opcao3Quantidade = :opcao3Quantidade")})
public class Enquete implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "enquete_id")
    @SerializedName("enqueteId")
    private Integer enqueteId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "pergunta")
    private String pergunta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "opcao_1")
    private String opcao1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "opcao_2")
    private String opcao2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "opcao_3")
    private String opcao3;
    @Basic(optional = false)
    @NotNull
    @Column(name = "opcao_1_quantidade")
    private int opcao1Quantidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "opcao_2_quantidade")
    private int opcao2Quantidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "opcao_3_quantidade")
    private int opcao3Quantidade;

    public Enquete() {
    }

    public Enquete(Integer enqueteId) {
        this.enqueteId = enqueteId;
    }

    public Enquete(Integer enqueteId, Date data, String pergunta, String opcao1, String opcao2, String opcao3, int opcao1Quantidade, int opcao2Quantidade, int opcao3Quantidade) {
        this.enqueteId = enqueteId;
        this.data = data;
        this.pergunta = pergunta;
        this.opcao1 = opcao1;
        this.opcao2 = opcao2;
        this.opcao3 = opcao3;
        this.opcao1Quantidade = opcao1Quantidade;
        this.opcao2Quantidade = opcao2Quantidade;
        this.opcao3Quantidade = opcao3Quantidade;
    }

    public Integer getEnqueteId() {
        return enqueteId;
    }

    public void setEnqueteId(Integer enqueteId) {
        this.enqueteId = enqueteId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getOpcao1() {
        return opcao1;
    }

    public void setOpcao1(String opcao1) {
        this.opcao1 = opcao1;
    }

    public String getOpcao2() {
        return opcao2;
    }

    public void setOpcao2(String opcao2) {
        this.opcao2 = opcao2;
    }

    public String getOpcao3() {
        return opcao3;
    }

    public void setOpcao3(String opcao3) {
        this.opcao3 = opcao3;
    }

    public int getOpcao1Quantidade() {
        return opcao1Quantidade;
    }

    public void setOpcao1Quantidade(int opcao1Quantidade) {
        this.opcao1Quantidade = opcao1Quantidade;
    }

    public int getOpcao2Quantidade() {
        return opcao2Quantidade;
    }

    public void setOpcao2Quantidade(int opcao2Quantidade) {
        this.opcao2Quantidade = opcao2Quantidade;
    }

    public int getOpcao3Quantidade() {
        return opcao3Quantidade;
    }

    public void setOpcao3Quantidade(int opcao3Quantidade) {
        this.opcao3Quantidade = opcao3Quantidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enqueteId != null ? enqueteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enquete)) {
            return false;
        }
        Enquete other = (Enquete) object;
        if ((this.enqueteId == null && other.enqueteId != null) || (this.enqueteId != null && !this.enqueteId.equals(other.enqueteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Enquete{" + "enqueteId=" + enqueteId + ", data=" + data + ", pergunta=" + pergunta + ", opcao1=" + opcao1 + ", opcao2=" + opcao2 + ", opcao3=" + opcao3 + ", opcao1Quantidade=" + opcao1Quantidade + ", opcao2Quantidade=" + opcao2Quantidade + ", opcao3Quantidade=" + opcao3Quantidade + '}';
    }
    
}
