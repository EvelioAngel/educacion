/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.educacion.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Evelio
 */
@Entity
@Table(name = "trabajador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trabajador.findAll", query = "SELECT t FROM Trabajador t")
    , @NamedQuery(name = "Trabajador.findByIdTrabajador", query = "SELECT t FROM Trabajador t WHERE t.idTrabajador = :idTrabajador")
    , @NamedQuery(name = "Trabajador.findByCi", query = "SELECT t FROM Trabajador t WHERE t.ci = :ci")
    , @NamedQuery(name = "Trabajador.findByNombre", query = "SELECT t FROM Trabajador t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Trabajador.findBySexo", query = "SELECT t FROM Trabajador t WHERE t.sexo = :sexo")
    , @NamedQuery(name = "Trabajador.findByRaza", query = "SELECT t FROM Trabajador t WHERE t.raza = :raza")
    , @NamedQuery(name = "Trabajador.findByDireccion", query = "SELECT t FROM Trabajador t WHERE t.direccion = :direccion")
    , @NamedQuery(name = "Trabajador.findByTelefono", query = "SELECT t FROM Trabajador t WHERE t.telefono = :telefono")
    , @NamedQuery(name = "Trabajador.findByCorreo", query = "SELECT t FROM Trabajador t WHERE t.correo = :correo")
    , @NamedQuery(name = "Trabajador.findByGrupoEscala", query = "SELECT t FROM Trabajador t WHERE t.grupoEscala = :grupoEscala")
    , @NamedQuery(name = "Trabajador.findByDocente", query = "SELECT t FROM Trabajador t WHERE t.docente = :docente")
    , @NamedQuery(name = "Trabajador.findByAnnosEsperiencia", query = "SELECT t FROM Trabajador t WHERE t.annosEsperiencia = :annosEsperiencia")
    , @NamedQuery(name = "Trabajador.findByMilitancia", query = "SELECT t FROM Trabajador t WHERE t.militancia = :militancia")
    , @NamedQuery(name = "Trabajador.findByFechaAlta", query = "SELECT t FROM Trabajador t WHERE t.fechaAlta = :fechaAlta")
    , @NamedQuery(name = "Trabajador.findByMotivoAlta", query = "SELECT t FROM Trabajador t WHERE t.motivoAlta = :motivoAlta")
    , @NamedQuery(name = "Trabajador.findByFechaBaja", query = "SELECT t FROM Trabajador t WHERE t.fechaBaja = :fechaBaja")
    , @NamedQuery(name = "Trabajador.findByMotivoBaja", query = "SELECT t FROM Trabajador t WHERE t.motivoBaja = :motivoBaja")})
public class Trabajador implements Serializable {

    @OneToMany(mappedBy = "idTrabajador")
    private Collection<TrabajadorHistorico> trabajadorHistoricoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_trabajador")
    private Integer idTrabajador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "ci")
    private String ci;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sexo")
    private Character sexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "raza")
    private String raza;
    @Size(max = 200)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 20)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 50)
    @Column(name = "correo")
    private String correo;
    @Size(max = 5)
    @Column(name = "grupo_escala")
    private String grupoEscala;
    @Basic(optional = false)
    @NotNull
    @Column(name = "docente")
    private boolean docente;
    @Column(name = "annos_esperiencia")
    private Integer annosEsperiencia;
    @Size(max = 10)
    @Column(name = "militancia")
    private String militancia;
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Size(max = 100)
    @Column(name = "motivo_alta")
    private String motivoAlta;
    @Column(name = "fecha_baja")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @Size(max = 100)
    @Column(name = "motivo_baja")
    private String motivoBaja;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
    @ManyToOne(optional = false)
    private Cargo idCargo;
    @JoinColumn(name = "id_categoria_cientifica", referencedColumnName = "id_categoria_cientifica")
    @ManyToOne(optional = false)
    private CategoriaCientifica idCategoriaCientifica;
    @JoinColumn(name = "id_categoria_ocupacional", referencedColumnName = "id_categoria_ocupacional")
    @ManyToOne(optional = false)
    private CategoriaOcupacional idCategoriaOcupacional;
    @JoinColumn(name = "id_ensenanza", referencedColumnName = "id_ensenanza")
    @ManyToOne(optional = false)
    private Ensenanza idEnsenanza;
    @JoinColumn(name = "id_entidad", referencedColumnName = "id_entidad")
    @ManyToOne(optional = false)
    private Entidad idEntidad;
    @JoinColumn(name = "id_especialidad", referencedColumnName = "id_especialidad")
    @ManyToOne(optional = false)
    private Especialidad idEspecialidad;
    @JoinColumn(name = "id_nivel_preparacion", referencedColumnName = "id_nivel_preparacion")
    @ManyToOne(optional = false)
    private NivelPreparacion idNivelPreparacion;

    public Trabajador() {
    }

    public Trabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public Trabajador(Integer idTrabajador, String ci, String nombre, Character sexo, String raza, boolean docente) {
        this.idTrabajador = idTrabajador;
        this.ci = ci;
        this.nombre = nombre;
        this.sexo = sexo;
        this.raza = raza;
        this.docente = docente;
    }

    public Integer getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getGrupoEscala() {
        return grupoEscala;
    }

    public void setGrupoEscala(String grupoEscala) {
        this.grupoEscala = grupoEscala;
    }

    public boolean getDocente() {
        return docente;
    }

    public void setDocente(boolean docente) {
        this.docente = docente;
    }

    public Integer getAnnosEsperiencia() {
        return annosEsperiencia;
    }

    public void setAnnosEsperiencia(Integer annosEsperiencia) {
        this.annosEsperiencia = annosEsperiencia;
    }

    public String getMilitancia() {
        return militancia;
    }

    public void setMilitancia(String militancia) {
        this.militancia = militancia;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getMotivoAlta() {
        return motivoAlta;
    }

    public void setMotivoAlta(String motivoAlta) {
        this.motivoAlta = motivoAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public Cargo getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargo idCargo) {
        this.idCargo = idCargo;
    }

    public CategoriaCientifica getIdCategoriaCientifica() {
        return idCategoriaCientifica;
    }

    public void setIdCategoriaCientifica(CategoriaCientifica idCategoriaCientifica) {
        this.idCategoriaCientifica = idCategoriaCientifica;
    }

    public CategoriaOcupacional getIdCategoriaOcupacional() {
        return idCategoriaOcupacional;
    }

    public void setIdCategoriaOcupacional(CategoriaOcupacional idCategoriaOcupacional) {
        this.idCategoriaOcupacional = idCategoriaOcupacional;
    }

    public Ensenanza getIdEnsenanza() {
        return idEnsenanza;
    }

    public void setIdEnsenanza(Ensenanza idEnsenanza) {
        this.idEnsenanza = idEnsenanza;
    }

    public Entidad getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Entidad idEntidad) {
        this.idEntidad = idEntidad;
    }

    public Especialidad getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Especialidad idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public NivelPreparacion getIdNivelPreparacion() {
        return idNivelPreparacion;
    }

    public void setIdNivelPreparacion(NivelPreparacion idNivelPreparacion) {
        this.idNivelPreparacion = idNivelPreparacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrabajador != null ? idTrabajador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trabajador)) {
            return false;
        }
        Trabajador other = (Trabajador) object;
        if ((this.idTrabajador == null && other.idTrabajador != null) || (this.idTrabajador != null && !this.idTrabajador.equals(other.idTrabajador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.educacion.model.Trabajador[ idTrabajador=" + idTrabajador + " ]";
    }

    @XmlTransient
    public Collection<TrabajadorHistorico> getTrabajadorHistoricoCollection() {
        return trabajadorHistoricoCollection;
    }

    public void setTrabajadorHistoricoCollection(Collection<TrabajadorHistorico> trabajadorHistoricoCollection) {
        this.trabajadorHistoricoCollection = trabajadorHistoricoCollection;
    }
    
}
