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
@Table(name = "ensenanza")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ensenanza.findAll", query = "SELECT e FROM Ensenanza e")
    , @NamedQuery(name = "Ensenanza.findByIdEnsenanza", query = "SELECT e FROM Ensenanza e WHERE e.idEnsenanza = :idEnsenanza")
    , @NamedQuery(name = "Ensenanza.findByDescripcion", query = "SELECT e FROM Ensenanza e WHERE e.descripcion = :descripcion")})
public class Ensenanza implements Serializable {

    @OneToMany(mappedBy = "idEnsenanza")
    private Collection<Trabajador> trabajadorCollection;
    @OneToMany(mappedBy = "idEnsenanza")
    private Collection<TrabajadorHistorico> trabajadorHistoricoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ensenanza")
    private Integer idEnsenanza;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;

    public Ensenanza() {
    }

    public Ensenanza(Integer idEnsenanza) {
        this.idEnsenanza = idEnsenanza;
    }

    public Ensenanza(Integer idEnsenanza, String descripcion) {
        this.idEnsenanza = idEnsenanza;
        this.descripcion = descripcion;
    }

    public Integer getIdEnsenanza() {
        return idEnsenanza;
    }

    public void setIdEnsenanza(Integer idEnsenanza) {
        this.idEnsenanza = idEnsenanza;
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
        hash += (idEnsenanza != null ? idEnsenanza.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ensenanza)) {
            return false;
        }
        Ensenanza other = (Ensenanza) object;
        if ((this.idEnsenanza == null && other.idEnsenanza != null) || (this.idEnsenanza != null && !this.idEnsenanza.equals(other.idEnsenanza))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.educacion.model.Ensenanza[ idEnsenanza=" + idEnsenanza + " ]";
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
