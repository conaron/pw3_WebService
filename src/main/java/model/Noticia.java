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
@Table(name = "noticia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Noticia.findAll", query = "SELECT n FROM Noticia n")
    , @NamedQuery(name = "Noticia.findByNoticiaId", query = "SELECT n FROM Noticia n WHERE n.noticiaId = :noticiaId")
    , @NamedQuery(name = "Noticia.findByTitulo", query = "SELECT n FROM Noticia n WHERE n.titulo = :titulo")
    , @NamedQuery(name = "Noticia.findByImagemUrl", query = "SELECT n FROM Noticia n WHERE n.imagemUrl = :imagemUrl")
    , @NamedQuery(name = "Noticia.findByManchete", query = "SELECT n FROM Noticia n WHERE n.manchete = :manchete")
    , @NamedQuery(name = "Noticia.findByNoticia", query = "SELECT n FROM Noticia n WHERE n.noticia = :noticia")})
public class Noticia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "noticia_id")
    private Integer noticiaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "imagem_url")
    private String imagemUrl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "manchete")
    private String manchete;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "noticia")
    private String noticia;

    public Noticia() {
    }

    public Noticia(Integer noticiaId) {
        this.noticiaId = noticiaId;
    }

    public Noticia(Integer noticiaId, String titulo, String imagemUrl, String manchete, String noticia) {
        this.noticiaId = noticiaId;
        this.titulo = titulo;
        this.imagemUrl = imagemUrl;
        this.manchete = manchete;
        this.noticia = noticia;
    }

    public Integer getNoticiaId() {
        return noticiaId;
    }

    public void setNoticiaId(Integer noticiaId) {
        this.noticiaId = noticiaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public String getManchete() {
        return manchete;
    }

    public void setManchete(String manchete) {
        this.manchete = manchete;
    }

    public String getNoticia() {
        return noticia;
    }

    public void setNoticia(String noticia) {
        this.noticia = noticia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noticiaId != null ? noticiaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Noticia)) {
            return false;
        }
        Noticia other = (Noticia) object;
        if ((this.noticiaId == null && other.noticiaId != null) || (this.noticiaId != null && !this.noticiaId.equals(other.noticiaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Noticia[ noticiaId=" + noticiaId + " ]";
    }
    
}
