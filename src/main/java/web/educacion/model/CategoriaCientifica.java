/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.educacion.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Evelio
 */
@Entity
@Table(name = "categoria_cientifica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaCientifica.findAll", query = "SELECT c FROM CategoriaCientifica c")
    , @NamedQuery(name = "CategoriaCientifica.findByIdCategoriaCientifica", query = "SELECT c FROM CategoriaCientifica c WHERE c.idCategoriaCientifica = :idCategoriaCientifica")
    , @NamedQuery(name = "CategoriaCientifica.findByDescripcion", query = "SELECT c FROM CategoriaCientifica c WHERE c.descripcion = :descripcion")})
public class CategoriaCientifica implements Serializable {

    @OneToMany(mappedBy = "idCategoriaCientifica")
    private Collection<Trabajador> trabajadorCollection;
    @OneToMany(mappedBy = "idCategoriaCientifica")
    private Collection<TrabajadorHistorico> trabajadorHistoricoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categoria_cientifica")
    private Integer idCategoriaCientifica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;

    public CategoriaCientifica() {
    }

    public CategoriaCientifica(Integer idCategoriaCientifica) {
        this.idCategoriaCientifica = idCategoriaCientifica;
    }

    public CategoriaCientifica(Integer idCategoriaCientifica, String descripcion) {
        this.idCategoriaCientifica = idCategoriaCientifica;
        this.descripcion = descripcion;
    }

    public Integer getIdCategoriaCientifica() {
        return idCategoriaCientifica;
    }

    public void setIdCategoriaCientifica(Integer idCategoriaCientifica) {
        this.idCategoriaCientifica = idCategoriaCientifica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoriaCientifica != null ? idCategoriaCientifica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaCientifica)) {
            return false;
        }
        CategoriaCientifica other = (CategoriaCientifica) object;
        if ((this.idCategoriaCientifica == null && other.idCategoriaCientifica != null) || (this.idCategoriaCientifica != null && !this.idCategoriaCientifica.equals(other.idCategoriaCientifica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.educacion.model.CategoriaCientifica[ idCategoriaCientifica=" + idCategoriaCientifica + " ]";
    }

    @XmlTransient
    public Collection<Trabajador> getTrabajadorCollection() {
        return trabajadorCollection;
    }

    public void setTrabajadorCollection(Collection<Trabajador> trabajadorCollection) {
        this.trabajadorCollection = trabajadorCollection;
    }

    @XmlTransient
    public Collection<TrabajadorHistorico> getTrabajadorHistoricoCollection() {
        return trabajadorHistoricoCollection;
    }

    public void setTrabajadorHistoricoCollection(Collection<TrabajadorHistorico> trabajadorHistoricoCollection) {
        this.trabajadorHistoricoCollection = trabajadorHistoricoCollection;
    }
    
}
