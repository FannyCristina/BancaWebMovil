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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Fanny
 */
@Entity
@Table(name = "intereses")
@NamedQueries({
    @NamedQuery(name = "Intereses.findAll", query = "SELECT i FROM Intereses i"),
    @NamedQuery(name = "Intereses.findById", query = "SELECT i FROM Intereses i WHERE i.id = :id"),
    @NamedQuery(name = "Intereses.findByTiempo", query = "SELECT i FROM Intereses i WHERE i.tiempo = :tiempo"),
    @NamedQuery(name = "Intereses.findByTasa", query = "SELECT i FROM Intereses i WHERE i.tasa = :tasa"),
    @NamedQuery(name = "Intereses.findByEmpleado", query = "SELECT i FROM Intereses i WHERE i.empleado = :empleado")})
public class Intereses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tiempo")
    private int tiempo;
    @Basic(optional = false)
    @Column(name = "tasa")
    private double tasa;
    @Basic(optional = false)
    @Column(name = "empleado")
    private int empleado;

    public Intereses() {
    }

    public Intereses(Integer id) {
        this.id = id;
    }

    public Intereses(Integer id, int tiempo, double tasa, int empleado) {
        this.id = id;
        this.tiempo = tiempo;
        this.tasa = tasa;
        this.empleado = empleado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
    }

    public int getEmpleado() {
        return empleado;
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
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
        if (!(object instanceof Intereses)) {
            return false;
        }
        Intereses other = (Intereses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ups.edu.ec.bancadis.modelo.Intereses[ id=" + id + " ]";
    }
    
}
