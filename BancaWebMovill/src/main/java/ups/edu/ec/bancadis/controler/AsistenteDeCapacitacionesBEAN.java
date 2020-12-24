/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.bancadis.controler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import ups.edu.ec.bancadis.modelo.Cliente;
import ups.edu.ec.bancadis.modelo.Empleado;
import ups.edu.ec.bancadis.modelo.Solicitud;
import ups.edu.ec.bancadis.on.ClienteON;
import ups.edu.ec.bancadis.on.EmpleadoON;
import ups.edu.ec.bancadis.on.SolicitudON;

/**
 *
 * @author Fanny
 */
@ManagedBean
@ViewScoped
public class AsistenteDeCapacitacionesBEAN {

    private Cliente cliete;
    private List<Solicitud> listaSolicitudes;
    private String textoBuscar;
    private double saldo;
    private String respuesta;
    private String resCedula;
    private String resTipo;
    private List<String> opciones;
    private String estado = "";

    public AsistenteDeCapacitacionesBEAN() {
    }

    @Inject
    private ClienteON clienteON;

    @Inject
    private SolicitudON solicitudON;


    @PostConstruct
    public void init() {
        opciones = Arrays.asList("CAMBIAR ESTADO", "APROBAR", "NEGAR");
        estado = "CAMBIAR ESTADO";
        cliete = new Cliente();
        listaSolicitudes = solicitudON.listarSalicitudes();
        textoBuscar = "";
        saldo = 0;
    }


    public String cambiarEstado(Solicitud solicitud) {
        if (estado.equals("NEGAR")) {
            //solicitud.setEstado("NEGADO");
            //solicitudON.actualizarSolicuitudNegada(solicitud);
        } else if (estado.equals("APROBAR")) {
           // solicitud.setEstado("APROBADA");
            //solicitudON.actualizarSolicuitudAceptada(solicitud);
        }
        init();
        return "";
    }


    public String formaRes(String r) {
        r = r.replace("{", "");
        r = r.replace(":", "");
        r = r.replace(",", "");

        String res[] = r.split("\"");
        for (String re : res) {
            System.out.println(re);
        }
        resCedula = res[2].replace(",", "");
        if (res[5].toString().equals("2")) {
            resTipo = "MALO";
        } else {
            resTipo = "BUENO";
        }
        //resTipo = res[5];
        return "";
    }

// -------------------> 
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }

    public Cliente getNewCliente() {
        return cliete;
    }

    public void setNewCliente(Cliente newCliente) {
        this.cliete = newCliente;
    }

    public ClienteON getClienteON() {
        return clienteON;
    }

    public void setClienteON(ClienteON clienteON) {
        this.clienteON = clienteON;
    }

    public String getTextoBuscar() {
        return textoBuscar;
    }

    public void setTextoBuscar(String textoBuscar) {
        this.textoBuscar = textoBuscar;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliete() {
        return cliete;
    }

    public void setCliete(Cliente cliete) {
        this.cliete = cliete;
    }

    public List<Solicitud> getListaSolicitudes() {
        return listaSolicitudes;
    }

    public void setListaSolicitudes(List<Solicitud> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }

    public SolicitudON getSolicitudON() {
        return solicitudON;
    }

    public void setSolicitudON(SolicitudON solicitudON) {
        this.solicitudON = solicitudON;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getResCedula() {
        return resCedula;
    }

    public void setResCedula(String resCedula) {
        this.resCedula = resCedula;
    }

    public String getResTipo() {
        return resTipo;
    }

    public void setResTipo(String resTipo) {
        this.resTipo = resTipo;
    }

}
