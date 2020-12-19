/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.banca.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fanny
 */
@Entity
@Table(name = "poliza")
@NamedQueries({
    @NamedQuery(name = "Poliza.findAll", query = "SELECT p FROM Poliza p"),
    @NamedQuery(name = "Poliza.findById", query = "SELECT p FROM Poliza p WHERE p.id = :id"),
    @NamedQuery(name = "Poliza.findByMonto", query = "SELECT p FROM Poliza p WHERE p.monto = :monto"),
    @NamedQuery(name = "Poliza.findByFechaInicio", query = "SELECT p FROM Poliza p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Poliza.findByFechaFin", query = "SELECT p FROM Poliza p WHERE p.fechaFin = :fechaFin"),
    @NamedQuery(name = "Poliza.findByInteres", query = "SELECT p FROM Poliza p WHERE p.interes = :interes"),
    @NamedQuery(name = "Poliza.findByFrecuencia", query = "SELECT p FROM Poliza p WHERE p.frecuencia = :frecuencia")})
public class Poliza implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "monto")
    private double monto;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @Column(name = "interes")
    private double interes;
    @Basic(optional = false)
    @Column(name = "frecuencia")
    private String frecuencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "polizaId")
    private Collection<Cuenta> cuentaCollection;
    @JoinColumn(name = "trabajador_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Trabajador trabajadorId;

    public Poliza() {
    }

    public Poliza(Integer id) {
        this.id = id;
    }

    public Poliza(Integer id, double monto, Date fechaInicio, Date fechaFin, double interes, String frecuencia) {
        this.id = id;
        this.monto = monto;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.interes = interes;
        this.frecuencia = frecuencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Collection<Cuenta> getCuentaCollection() {
        return cuentaCollection;
    }

    public void setCuentaCollection(Collection<Cuenta> cuentaCollection) {
        this.cuentaCollection = cuentaCollection;
    }

    public Trabajador getTrabajadorId() {
        return trabajadorId;
    }

    public void setTrabajadorId(Trabajador trabajadorId) {
        this.trabajadorId = trabajadorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Poliza)) {
            return false;
        }
        Poliza other = (Poliza) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ups.edu.ec.banca.modelo.Poliza[ id=" + id + " ]";
    }
    
}
