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
@Table(name = "cuenta")
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c"),
    @NamedQuery(name = "Cuenta.findById", query = "SELECT c FROM Cuenta c WHERE c.id = :id"),
    @NamedQuery(name = "Cuenta.findByNumero", query = "SELECT c FROM Cuenta c WHERE c.numero = :numero"),
    @NamedQuery(name = "Cuenta.findBySaldo", query = "SELECT c FROM Cuenta c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "Cuenta.findByFecha", query = "SELECT c FROM Cuenta c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Cuenta.findByEliminado", query = "SELECT c FROM Cuenta c WHERE c.eliminado = :eliminado")})
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @Column(name = "saldo")
    private double saldo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "eliminado")
    private Short eliminado;
    @JoinColumn(name = "cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta")
    private Collection<Credito> creditoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentaid")
    private Collection<Transaciones> transacionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenante")
    private Collection<Transferencias> transferenciasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beneficiario")
    private Collection<Transferencias> transferenciasCollection1;

    public Cuenta() {
    }

    public Cuenta(Integer id) {
        this.id = id;
    }

    public Cuenta(Integer id, String numero, double saldo, Date fecha) {
        this.id = id;
        this.numero = numero;
        this.saldo = saldo;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Short getEliminado() {
        return eliminado;
    }

    public void setEliminado(Short eliminado) {
        this.eliminado = eliminado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Collection<Credito> getCreditoCollection() {
        return creditoCollection;
    }

    public void setCreditoCollection(Collection<Credito> creditoCollection) {
        this.creditoCollection = creditoCollection;
    }

    public Collection<Transaciones> getTransacionesCollection() {
        return transacionesCollection;
    }

    public void setTransacionesCollection(Collection<Transaciones> transacionesCollection) {
        this.transacionesCollection = transacionesCollection;
    }

    public Collection<Transferencias> getTransferenciasCollection() {
        return transferenciasCollection;
    }

    public void setTransferenciasCollection(Collection<Transferencias> transferenciasCollection) {
        this.transferenciasCollection = transferenciasCollection;
    }

    public Collection<Transferencias> getTransferenciasCollection1() {
        return transferenciasCollection1;
    }

    public void setTransferenciasCollection1(Collection<Transferencias> transferenciasCollection1) {
        this.transferenciasCollection1 = transferenciasCollection1;
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
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ups.edu.ec.banca.modelo.Cuenta[ id=" + id + " ]";
    }
    
}
