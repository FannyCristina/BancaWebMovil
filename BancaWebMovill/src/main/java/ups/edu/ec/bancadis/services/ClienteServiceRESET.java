/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.bancadis.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import ups.edu.ec.bancadis.controler.ClienteBEAN;
import ups.edu.ec.bancadis.modelo.Cliente;
import ups.edu.ec.bancadis.on.ClienteON;

/**
 *
 * @author Fanny
 */
@Path("/cliente")
public class ClienteServiceRESET {

    @Inject
    private ClienteON clienteON;

    public String transferencia() {
        return null;
    }

    Cliente auxCliente = new Cliente();

    @GET
    @Path("/deposito")
    @Produces("application/json")
    public Mensaje deposito(@QueryParam("cedula") String cedula, @QueryParam("cantidad") double cantidad) {
        Mensaje msj = new Mensaje();
        try {
            System.out.println("<<< Ya  esta corriendo el reste");
            auxCliente = buscaClienteID(cedula);
            BigDecimal bd = new BigDecimal(auxCliente.getCuentaList().get(0).getSaldo() + cantidad);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            auxCliente.getCuentaList().get(0).setSaldo(bd.doubleValue());
            clienteON.actualizarClienteTrasaccion(auxCliente, "Deposito", new BigDecimal(cantidad).doubleValue());
            msj.setCode("10");
            msj.setMensaje("Deposito Satisfactorio");
            return msj;
        } catch (Exception e) {
            e.printStackTrace();
            msj.setCode("0");
            msj.setMensaje("No se encuentra la cuenta "+e.getMessage());
            return msj;
        }

    }

    public Cliente buscaClienteID(String cedula) {
        try {
            //auxCliente = clienteON.buscarCliente(id);
            auxCliente = clienteON.buscarClienteCedula(cedula);
        } catch (Exception ex) {
            Logger.getLogger(ClienteBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auxCliente;
    }

    @GET
    @Path("/retiro")
    @Produces("application/json")
    public Mensaje retiro(@QueryParam("cedula") String cedula, @QueryParam("cantidad") double cantidad) {
        Mensaje msj= new Mensaje();
        try {
            auxCliente = buscaClienteID(cedula);
            BigDecimal bd = new BigDecimal(auxCliente.getCuentaList().get(0).getSaldo() - cantidad);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            auxCliente.getCuentaList().get(0).setSaldo(bd.doubleValue());
            clienteON.actualizarClienteTrasaccion(auxCliente, "Retiro", new BigDecimal(cantidad).doubleValue());
            msj.setCode("11");
            msj.setMensaje("Retiro Satisfactorio");
            return msj ;
        } catch (Exception e) {
             e.printStackTrace();
            msj.setCode("1");
            msj.setMensaje("No fue posible realizar el deposito");
            return msj;
        }

    }

    @GET
    @Path("/transferencia")
    @Produces("application/json")
    public Mensaje transferenciaLocal(@QueryParam("cliorigen") String clienteOrigen, @QueryParam("clidestino") String clienteDestino, @QueryParam("cantidad") double cantidad) {
        Mensaje msj = new Mensaje();
        try {
            //Rebjar saldo
            String aux = null;
            auxCliente = buscaClienteID(clienteOrigen);
            BigDecimal bdO = new BigDecimal(auxCliente.getCuentaList().get(0).getSaldo() - cantidad);
            bdO = bdO.setScale(2, RoundingMode.HALF_UP);
            auxCliente.getCuentaList().get(0).setSaldo(bdO.doubleValue());
            clienteON.actualizarClienteTrasaccion(auxCliente, "Transferencia", new BigDecimal(cantidad).doubleValue());
            aux = "Cta Debito: " + auxCliente.getCuentaList().get(0).getNumerocuenta()+ " , Saldo Actual: " + bdO;
            //Inbrementar saldo cta destino
            auxCliente = buscaClienteID(clienteDestino);
            BigDecimal bdD = new BigDecimal(auxCliente.getCuentaList().get(0).getSaldo() + cantidad);
            bdD = bdD.setScale(2, RoundingMode.HALF_UP);
            auxCliente.getCuentaList().get(0).setSaldo(bdD.doubleValue());
            clienteON.actualizarClienteTrasaccion(auxCliente, "Transferencia", new BigDecimal(cantidad).doubleValue());
            aux = aux + "\n Cta Beneficiaria: " + auxCliente.getCuentaList().get(0).getNumerocuenta()+ " , Saldo Actual: " + bdD;
            msj.setCode("12");
            msj.setMensaje("Transferencia  Satisfactorio");
            return msj;
        } catch (Exception e) {
            e.printStackTrace();
            msj.setCode("2");
            msj.setMensaje("No fue posible realizar la transferencia");
            return msj;
        }
    }

}
