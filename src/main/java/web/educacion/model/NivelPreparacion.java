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
@Table(name = "nivel_preparacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelPreparacion.findAll", query = "SELECT n FROM NivelPreparacion n")
    , @NamedQuery(name = "NivelPreparacion.findByIdNivelPreparacion", query = "SELECT n FROM NivelPreparacion n WHERE n.idNivelPreparacion = :idNivelPreparacion")
    , @NamedQuery(name = "NivelPreparacion.findByDescripcion", query = "SELECT n FROM NivelPreparacion n WHERE n.descripcion = :descripcion")})
public class NivelPreparacion implements Serializable {

    @OneToMany(mappedBy = "idNivelPreparacion")
    private Collection<Trabajador> trabajadorCollection;
    @OneToMany(mappedBy = "idNivelPreparacion")
    private Collection<TrabajadorHistorico> trabajadorHistoricoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nivel_preparacion")
    private Integer idNivelPreparacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;

    public NivelPreparacion() {
    }

    public NivelPreparacion(Integer idNivelPreparacion) {
        this.idNivelPreparacion = idNivelPreparacion;
    }

    public NivelPreparacion(Integer idNivelPreparacion, String descripcion) {
        this.idNivelPreparacion = idNivelPreparacion;
        this.descripcion = descripcion;
    }

    public Integer getIdNivelPreparacion() {
        return idNivelPreparacion;
    }

    public void setIdNivelPreparacion(Integer idNivelPreparacion) {
        this.idNivelPreparacion = idNivelPreparacion;
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
        hash += (idNivelPreparacion != null ? idNivelPreparacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelPreparacion)) {
            return false;
        }
        NivelPreparacion other = (NivelPreparacion) object;
        if ((this.idNivelPreparacion == null && other.idNivelPreparacion != null) || (this.idNivelPreparacion != null && !this.idNivelPreparacion.equals(other.idNivelPreparacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.educacion.model.NivelPreparacion[ idNivelPreparacion=" + idNivelPreparacion + " ]";
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
