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
public class AdministradorBEAN {

    private Empleado newTrabajador;
    private Empleado auxTrabajador;
    private List<Empleado> listaTrabajadores;
    private ArrayList<String> listaOpc;
    private String textoBuscar;
    private String mensaje;

    public AdministradorBEAN() {
    }

    @Inject
    private EmpleadoON trabajadorON;

    @PostConstruct
    public void init() {

        listaOpc = new ArrayList<>();
        listaOpc.add("Administrador");
        listaOpc.add("Cajero");
        listaOpc.add("Asistente");
        listaTrabajadores = trabajadorON.listaTrabajadores();
        textoBuscar = "";
        auxTrabajador = new Empleado();
        newTrabajador = new Empleado();

    }

    /**
     * Permiten guardar trabajador Consume la logica de negocio del on
     *
     * @return
     */
    public String guardarTrabajador() {
        try {
            trabajadorON.guardarTrabajador(newTrabajador);
            init();
        } catch (Exception ex) {
            mensaje = ex.getMessage();
            Logger.getLogger(TrabajadorBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Busca trabajadores desde el texto ingresado desde la vista
     *
     * @return
     */
    public String buscaTrabajadores() {
        System.out.println(textoBuscar);
        try {
            listaTrabajadores = trabajadorON.listaTrabajadoresCodigo(textoBuscar);
        } catch (Exception ex) {
            Logger.getLogger(TrabajadorBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Buscar trabajador mediante el id
     *
     * @param id
     * @return
     */
    public String buscaTrabajadorID(String id) {
        try {
            auxTrabajador = trabajadorON.buscarTrabajador(id);
            System.out.println("hireS");
        } catch (Exception ex) {
            Logger.getLogger(TrabajadorBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Actualiza el trabajador pasandole el auxtrabajador y llama al init
     *
     * @return
     */
    public String actualizarTrabajador() {
        trabajadorON.actualizarTrabajador(auxTrabajador);
        init();
        System.out.println("actualizado");
        return null;
    }
    

    /**
     * Elimina trabajador le pasa al objeto de negocio el objeto trabajador
     *
     * @return
     */
    public String eliminarTrabajador() {
        auxTrabajador.setEliminado(true);
        trabajadorON.actualizarTrabajador(auxTrabajador);
        init();
        System.out.println("Eliminado");
        return null;
    }

    // -------------------> 
    public Empleado getNewTrabajador() {
        return newTrabajador;
    }

    public void setNewTrabajador(Empleado newTrabajador) {
        this.newTrabajador = newTrabajador;
    }

    public EmpleadoON getTrabajadorON() {
        return trabajadorON;
    }

    public void setTrabajadorON(EmpleadoON trabajadorON) {
        this.trabajadorON = trabajadorON;
    }

    public ArrayList<String> getListaOpc() {
        return listaOpc;
    }

    public void setListaOpc(ArrayList<String> listaOpc) {
        this.listaOpc = listaOpc;
    }

    public List<Empleado> getListaTrabajadores() {
        return listaTrabajadores;
    }

    public void setListaTrabajadores(List<Empleado> listaTrabajadores) {
        this.listaTrabajadores = listaTrabajadores;
    }

    public String getTextoBuscar() {
        return textoBuscar;
    }

    public void setTextoBuscar(String textoBuscar) {
        this.textoBuscar = textoBuscar;
    }

    public Empleado getAuxTrabajador() {
        return auxTrabajador;
    }

    public void setAuxTrabajador(Empleado auxTrabajador) {
        this.auxTrabajador = auxTrabajador;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
