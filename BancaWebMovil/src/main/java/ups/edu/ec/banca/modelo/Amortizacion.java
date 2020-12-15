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
@Table(name = "amortizacion")
@NamedQueries({
    @NamedQuery(name = "Amortizacion.findAll", query = "SELECT a FROM Amortizacion a"),
    @NamedQuery(name = "Amortizacion.findById", query = "SELECT a FROM Amortizacion a WHERE a.id = :id"),
    @NamedQuery(name = "Amortizacion.findByPeriodo", query = "SELECT a FROM Amortizacion a WHERE a.periodo = :periodo"),
    @NamedQuery(name = "Amortizacion.findByCouta", query = "SELECT a FROM Amortizacion a WHERE a.couta = :couta"),
    @NamedQuery(name = "Amortizacion.findByInteres", query = "SELECT a FROM Amortizacion a WHERE a.interes = :interes"),
    @NamedQuery(name = "Amortizacion.findByTotal", query = "SELECT a FROM Amortizacion a WHERE a.total = :total"),
    @NamedQuery(name = "Amortizacion.findByDeuda", query = "SELECT a FROM Amortizacion a WHERE a.deuda = :deuda"),
    @NamedQuery(name = "Amortizacion.findByEstado", query = "SELECT a FROM Amortizacion a WHERE a.estado = :estado"),
    @NamedQuery(name = "Amortizacion.findByFecha", query = "SELECT a FROM Amortizacion a WHERE a.fecha = :fecha")})
public class Amortizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "periodo")
    private int periodo;
    @Basic(optional = false)
    @Column(name = "couta")
    private double couta;
    @Basic(optional = false)
    @Column(name = "interes")
    private double interes;
    @Basic(optional = false)
    @Column(name = "total")
    private double total;
    @Basic(optional = false)
    @Column(name = "deuda")
    private double deuda;
    @Basic(optional = false)
    @Column(name = "estado")
    private short estado;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "credito", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Credito credito;

    public Amortizacion() {
    }

    public Amortizacion(Integer id) {
        this.id = id;
    }

    public Amortizacion(Integer id, int periodo, double couta, double interes, double total, double deuda, short estado, Date fecha) {
        this.id = id;
        this.periodo = periodo;
        this.couta = couta;
        this.interes = interes;
        this.total = total;
        this.deuda = deuda;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public double getCouta() {
        return couta;
    }

    public void setCouta(double couta) {
        this.couta = couta;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDeuda() {
        return deuda;
    }

    public void setDeuda(double deuda) {
        this.deuda = deuda;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
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
        if (!(object instanceof Amortizacion)) {
            return false;
        }
        Amortizacion other = (Amortizacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ups.edu.ec.banca.modelo.Amortizacion[ id=" + id + " ]";
    }
    
}