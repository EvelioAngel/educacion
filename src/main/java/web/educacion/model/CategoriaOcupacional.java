/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.educacion.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "categoria_ocupacional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaOcupacional.findAll", query = "SELECT c FROM CategoriaOcupacional c")
    , @NamedQuery(name = "CategoriaOcupacional.findByIdCategoriaOcupacional", query = "SELECT c FROM CategoriaOcupacional c WHERE c.idCategoriaOcupacional = :idCategoriaOcupacional")
    , @NamedQuery(name = "CategoriaOcupacional.findByDescripcion", query = "SELECT c FROM CategoriaOcupacional c WHERE c.descripcion = :descripcion")})
public class CategoriaOcupacional implements Serializable {

    @OneToMany(mappedBy = "idCategoriaOcupacional")
    private Collection<Trabajador> trabajadorCollection;
    @OneToMany(mappedBy = "idCategoriaOcupacional")
    private Collection<TrabajadorHistorico> trabajadorHistoricoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categoria_ocupacional")
    private Integer idCategoriaOcupacional;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;

    public CategoriaOcupacional() {
    }

    public CategoriaOcupacional(Integer idCategoriaOcupacional) {
        this.idCategoriaOcupacional = idCategoriaOcupacional;
    }

    public CategoriaOcupacional(Integer idCategoriaOcupacional, String descripcion) {
        this.idCategoriaOcupacional = idCategoriaOcupacional;
        this.descripcion = descripcion;
    }

    public Integer getIdCategoriaOcupacional() {
        return idCategoriaOcupacional;
    }

    public void setIdCategoriaOcupacional(Integer idCategoriaOcupacional) {
        this.idCategoriaOcupacional = idCategoriaOcupacional;
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
        hash += (idCategoriaOcupacional != null ? idCategoriaOcupacional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaOcupacional)) {
            return false;
        }
        CategoriaOcupacional other = (CategoriaOcupacional) object;
        if ((this.idCategoriaOcupacional == null && other.idCategoriaOcupacional != null) || (this.idCategoriaOcupacional != null && !this.idCategoriaOcupacional.equals(other.idCategoriaOcupacional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.educacion.model.CategoriaOcupacional[ idCategoriaOcupacional=" + idCategoriaOcupacional + " ]";
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
