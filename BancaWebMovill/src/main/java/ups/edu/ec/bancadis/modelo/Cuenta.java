/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.bancadis.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @NamedQuery(name = "Cuenta.maxId", query = "SELECT MAX(c.id) FROM Cuenta c"),
    @NamedQuery(name = "Cuenta.findByTipoCuenta", query = "SELECT c FROM Cuenta c WHERE c.tipoCuenta = :tipoCuenta"),
    @NamedQuery(name = "Cuenta.findByNumerocuenta", query = "SELECT c FROM Cuenta c WHERE c.numerocuenta = :numerocuenta"),
    @NamedQuery(name = "Cuenta.findByFechaapertura", query = "SELECT c FROM Cuenta c WHERE c.fechaapertura = :fechaapertura"),
    @NamedQuery(name = "Cuenta.findBySaldo", query = "SELECT c FROM Cuenta c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "Cuenta.findByEmpleado", query = "SELECT c FROM Cuenta c WHERE c.empleado = :empleado"),
    @NamedQuery(name = "Cuenta.findByElimado", query = "SELECT c FROM Cuenta c WHERE c.elimado = :elimado")})
public class Cuenta implements Serializable {

    @Basic(optional = false)
    @Column(name = "elimado")
    private boolean elimado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta")
    private List<Poliza> polizaLista;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tipo_cuenta")
    private String tipoCuenta;
    @Basic(optional = false)
    @Column(name = "numerocuenta")
    private String numerocuenta;
    @Basic(optional = false)
    @Column(name = "fechaapertura")
    @Temporal(TemporalType.DATE)
    private Date fechaapertura;
    
    @Basic(optional = false)
    @Column(name = "saldo")
    private double saldo;
    
    @Basic(optional = false)
    @Column(name = "empleado")
    private int empleado;
    
    @JoinColumn(name ="id_cliente", referencedColumnName= "id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cliente cliente;
    
    

    public Cuenta() {
    }

    public Cuenta(Integer id) {
        this.id = id;
    }

    public Cuenta(Integer id, String tipoCuenta, String numerocuenta, Date fechaapertura, double saldo, int empleado, boolean elimado) {
        this.id = id;
        this.tipoCuenta = tipoCuenta;
        this.numerocuenta = numerocuenta;
        this.fechaapertura = fechaapertura;
        this.saldo = saldo;
        this.empleado = empleado;
        this.elimado = elimado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getNumerocuenta() {
        return numerocuenta;
    }

    public void setNumerocuenta(String numerocuenta) {
        this.numerocuenta = numerocuenta;
    }

    public Date getFechaapertura() {
        return fechaapertura;
    }

    public void setFechaapertura(Date fechaapertura) {
        this.fechaapertura = fechaapertura;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getEmpleado() {
        return empleado;
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
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
        return "ups.edu.ec.bancadis.modelo.Cuenta[ id=" + id + " ]";
    }

    public boolean getElimado() {
        return elimado;
    }

    public void setElimado(boolean elimado) {
        this.elimado = elimado;
    }

    public List<Poliza> getPolizaLista() {
        return polizaLista;
    }

    public void setPolizaLista(List<Poliza> polizaLista) {
        this.polizaLista = polizaLista;
    }



}
