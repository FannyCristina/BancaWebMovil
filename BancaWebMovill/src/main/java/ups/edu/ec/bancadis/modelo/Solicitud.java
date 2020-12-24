/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.bancadis.modelo;

import java.io.Serializable;
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
    @NamedQuery(name = "Solicitud.findByFrecuencia", query = "SELECT s FROM Solicitud s WHERE s.frecuencia = :frecuencia"),
    @NamedQuery(name = "Solicitud.findByPlazo", query = "SELECT s FROM Solicitud s WHERE s.plazo = :plazo"),
    @NamedQuery(name = "Solicitud.findByTasaInteres", query = "SELECT s FROM Solicitud s WHERE s.tasaInteres = :tasaInteres"),
    @NamedQuery(name = "Solicitud.findByFechaInicio", query = "SELECT s FROM Solicitud s WHERE s.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Solicitud.findByFechaFin", query = "SELECT s FROM Solicitud s WHERE s.fechaFin = :fechaFin"),
    @NamedQuery(name = "Solicitud.findByCopiacedula", query = "SELECT s FROM Solicitud s WHERE s.copiacedula = :copiacedula"),
    @NamedQuery(name = "Solicitud.findByPlanillaServBasicos", query = "SELECT s FROM Solicitud s WHERE s.planillaServBasicos = :planillaServBasicos"),
    @NamedQuery(name = "Solicitud.findByEliminado", query = "SELECT s FROM Solicitud s WHERE s.eliminado = :eliminado"),
    @NamedQuery(name = "Solicitud.findByDocumento", query = "SELECT s FROM Solicitud s WHERE s.documento = :documento"),
    @NamedQuery(name = "Solicitud.findByMonto", query = "SELECT s FROM Solicitud s WHERE s.monto = :monto")})
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "frecuencia")
    private String frecuencia;
    @Basic(optional = false)
    @Column(name = "plazo")
    private int plazo;
    @Basic(optional = false)
    @Column(name = "tasa_interes")
    private double tasaInteres;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @Column(name = "copiacedula")
    private String copiacedula;
    @Basic(optional = false)
    @Column(name = "planilla_serv_basicos")
    private String planillaServBasicos;
    @Column(name = "eliminado")
    private boolean eliminado;
    @Basic(optional = false)
    @Column(name = "documento")
    private boolean documento;
    @Basic(optional = false)
    @Column(name = "monto")
    private double monto;
    @JoinColumn(name = "cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente cliente;

    public Solicitud() {
    }

    public Solicitud(Integer id) {
        this.id = id;
    }

    public Solicitud(Integer id, String frecuencia, int plazo, double tasaInteres, Date fechaInicio, Date fechaFin, String copiacedula, String planillaServBasicos, boolean documento, double monto) {
        this.id = id;
        this.frecuencia = frecuencia;
        this.plazo = plazo;
        this.tasaInteres = tasaInteres;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.copiacedula = copiacedula;
        this.planillaServBasicos = planillaServBasicos;
        this.documento = documento;
        this.monto = monto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
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

    public String getCopiacedula() {
        return copiacedula;
    }

    public void setCopiacedula(String copiacedula) {
        this.copiacedula = copiacedula;
    }

    public String getPlanillaServBasicos() {
        return planillaServBasicos;
    }

    public void setPlanillaServBasicos(String planillaServBasicos) {
        this.planillaServBasicos = planillaServBasicos;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

  

    public boolean getDocumento() {
        return documento;
    }

    public void setDocumento(boolean documento) {
        this.documento = documento;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        return "ups.edu.ec.bancadis.modelo.Solicitud[ id=" + id + " ]";
    }
    
}
