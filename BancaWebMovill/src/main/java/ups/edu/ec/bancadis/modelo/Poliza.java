/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.bancadis.modelo;

import java.io.Serializable;
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
    @NamedQuery(name = "Poliza.findByPlazo", query = "SELECT p FROM Poliza p WHERE p.plazo = :plazo"),
    @NamedQuery(name = "Poliza.findByInteresGanado", query = "SELECT p FROM Poliza p WHERE p.interesGanado = :interesGanado"),
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
    @Column(name = "plazo")
    private int plazo;
    @Column(name = "interes_ganado")
    private Double interesGanado;
    @Basic(optional = false)
    @Column(name = "frecuencia")
    private String frecuencia;
    @JoinColumn(name = "cuenta", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cuenta cuenta;

    public Poliza() {
    }

    public Poliza(Integer id) {
        this.id = id;
    }

    public Poliza(Integer id, double monto, int plazo, String frecuencia) {
        this.id = id;
        this.monto = monto;
        this.plazo = plazo;
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

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public Double getInteresGanado() {
        return interesGanado;
    }

    public void setInteresGanado(Double interesGanado) {
        this.interesGanado = interesGanado;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
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
        return "ups.edu.ec.bancadis.modelo.Poliza[ id=" + id + " ]";
    }
    
}
