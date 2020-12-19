/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.banca.modelo;

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

/**
 *
 * @author Fanny
 */
@Entity
@Table(name = "tasa_interes")
@NamedQueries({
    @NamedQuery(name = "TasaInteres.findAll", query = "SELECT t FROM TasaInteres t"),
    @NamedQuery(name = "TasaInteres.findById", query = "SELECT t FROM TasaInteres t WHERE t.id = :id"),
    @NamedQuery(name = "TasaInteres.findByPlazo", query = "SELECT t FROM TasaInteres t WHERE t.plazo = :plazo"),
    @NamedQuery(name = "TasaInteres.findByTasa", query = "SELECT t FROM TasaInteres t WHERE t.tasa = :tasa")})
public class TasaInteres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "plazo")
    private int plazo;
    @Basic(optional = false)
    @Column(name = "tasa")
    private double tasa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTasaInteres")
    private Collection<Trabajador> trabajadorCollection;
    @JoinColumn(name = "trabajador_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Trabajador trabajadorId;

    public TasaInteres() {
    }

    public TasaInteres(Integer id) {
        this.id = id;
    }

    public TasaInteres(Integer id, int plazo, double tasa) {
        this.id = id;
        this.plazo = plazo;
        this.tasa = tasa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
    }

    public Collection<Trabajador> getTrabajadorCollection() {
        return trabajadorCollection;
    }

    public void setTrabajadorCollection(Collection<Trabajador> trabajadorCollection) {
        this.trabajadorCollection = trabajadorCollection;
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
        if (!(object instanceof TasaInteres)) {
            return false;
        }
        TasaInteres other = (TasaInteres) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ups.edu.ec.banca.modelo.TasaInteres[ id=" + id + " ]";
    }
    
}
