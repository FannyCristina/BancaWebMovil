/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.banca.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fanny
 */
@Entity
@Table(name = "solicitud")
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s"),
    @NamedQuery(name = "Solicitud.findById", query = "SELECT s FROM Solicitud s WHERE s.id = :id"),
    @NamedQuery(name = "Solicitud.findByMonto", query = "SELECT s FROM Solicitud s WHERE s.monto = :monto"),
    @NamedQuery(name = "Solicitud.findByFrecuencia", query = "SELECT s FROM Solicitud s WHERE s.frecuencia = :frecuencia"),
    @NamedQuery(name = "Solicitud.findByPlazo", query = "SELECT s FROM Solicitud s WHERE s.plazo = :plazo"),
    @NamedQuery(name = "Solicitud.findByTasainteres", query = "SELECT s FROM Solicitud s WHERE s.tasainteres = :tasainteres"),
    @NamedQuery(name = "Solicitud.findByFechaInicio", query = "SELECT s FROM Solicitud s WHERE s.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Solicitud.findByFechaFin", query = "SELECT s FROM Solicitud s WHERE s.fechaFin = :fechaFin"),
    @NamedQuery(name = "Solicitud.findByIdCuenta", query = "SELECT s FROM Solicitud s WHERE s.idCuenta = :idCuenta")})
public class Solicitud implements Serializable {

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
    @Column(name = "frecuencia")
    private String frecuencia;
    @Basic(optional = false)
    @Column(name = "plazo")
    private int plazo;
    @Basic(optional = false)
    @Column(name = "tasainteres")
    private double tasainteres;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @Lob
    @Column(name = "copia_cedula")
    private byte[] copiaCedula;
    @Basic(optional = false)
    @Lob
    @Column(name = "planilla")
    private byte[] planilla;
    @Basic(optional = false)
    @Column(name = "id_cuenta")
    private int idCuenta;
    @JoinColumn(name = "trabajador_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Trabajador trabajadorId;

    public Solicitud() {
    }

    public Solicitud(Integer id) {
        this.id = id;
    }

    public Solicitud(Integer id, double monto, String frecuencia, int plazo, double tasainteres, Date fechaInicio, Date fechaFin, byte[] copiaCedula, byte[] planilla, int idCuenta) {
        this.id = id;
        this.monto = monto;
        this.frecuencia = frecuencia;
        this.plazo = plazo;
        this.tasainteres = tasainteres;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.copiaCedula = copiaCedula;
        this.planilla = planilla;
        this.idCuenta = idCuenta;
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

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public double getTasainteres() {
        return tasainteres;
    }

    public void setTasainteres(double tasainteres) {
        this.tasainteres = tasainteres;
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

    public byte[] getCopiaCedula() {
        return copiaCedula;
    }

    public void setCopiaCedula(byte[] copiaCedula) {
        this.copiaCedula = copiaCedula;
    }

    public byte[] getPlanilla() {
        return planilla;
    }

    public void setPlanilla(byte[] planilla) {
        this.planilla = planilla;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
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
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ups.edu.ec.banca.modelo.Solicitud[ id=" + id + " ]";
    }
    
}
