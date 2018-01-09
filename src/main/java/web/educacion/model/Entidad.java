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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "entidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entidad.findAll", query = "SELECT e FROM Entidad e")
    , @NamedQuery(name = "Entidad.findByIdEntidad", query = "SELECT e FROM Entidad e WHERE e.idEntidad = :idEntidad")
    , @NamedQuery(name = "Entidad.findByCodigo", query = "SELECT e FROM Entidad e WHERE e.codigo = :codigo")
    , @NamedQuery(name = "Entidad.findByDescripcion", query = "SELECT e FROM Entidad e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "Entidad.findBySigla", query = "SELECT e FROM Entidad e WHERE e.sigla = :sigla")
    , @NamedQuery(name = "Entidad.findBySubordinada", query = "SELECT e FROM Entidad e WHERE e.subordinada = :subordinada")})
public class Entidad implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo")
    private int codigo;
    @OneToMany(mappedBy = "idEntidad")
    private Collection<Trabajador> trabajadorCollection;
    @OneToMany(mappedBy = "idEntidad")
    private Collection<TrabajadorHistorico> trabajadorHistoricoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_entidad")
    private Integer idEntidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 30)
    @Column(name = "sigla")
    private String sigla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subordinada")
    private boolean subordinada;
    @OneToMany(mappedBy = "entidadSubordina")
    private Collection<Entidad> entidadCollection;
    @JoinColumn(name = "entidad_subordina", referencedColumnName = "id_entidad")
    @ManyToOne
    private Entidad entidadSubordina;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne(optional = false)
    private Municipio idMunicipio;

    public Entidad() {
    }

    public Entidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }

    public Entidad(Integer idEntidad, Integer codigo, String descripcion, boolean subordinada) {
        this.idEntidad = idEntidad;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.subordinada = subordinada;
    }

    public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public boolean getSubordinada() {
        return subordinada;
    }

    public void setSubordinada(boolean subordinada) {
        this.subordinada = subordinada;
    }

    @XmlTransient
    public Collection<Entidad> getEntidadCollection() {
        return entidadCollection;
    }

    public void setEntidadCollection(Collection<Entidad> entidadCollection) {
        this.entidadCollection = entidadCollection;
    }

    public Entidad getEntidadSubordina() {
        return entidadSubordina;
    }

    public void setEntidadSubordina(Entidad entidadSubordina) {
        this.entidadSubordina = entidadSubordina;
    }

    public Municipio getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Municipio idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntidad != null ? idEntidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entidad)) {
            return false;
        }
        Entidad other = (Entidad) object;
        if ((this.idEntidad == null && other.idEntidad != null) || (this.idEntidad != null && !this.idEntidad.equals(other.idEntidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.educacion.model.Entidad[ idEntidad=" + idEntidad + " ]";
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
}
