/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.bancadis.on;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;
import ups.edu.ec.bancadis.doa.PolizaDAO;
import ups.edu.ec.bancadis.modelo.Intereses;
import ups.edu.ec.bancadis.modelo.Poliza;
import ups.edu.ec.bancadis.modelo.Solicitud;

/**
 *
 * @author Fanny
 */
@Stateless
public class PolizaON {

    @Inject
    PolizaDAO polizaDAO;
    

public void actualizarPoliza(Poliza poliza){
        try {
            polizaDAO.update(poliza);
        } catch (Exception ex) {
            Logger.getLogger(PolizaON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Poliza> listarPolizasCliente(int id){
        try {
            return (List<Poliza>) polizaDAO.findByID(id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PolizaON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Poliza> listarCreditos(){
        try {
            return polizaDAO.findAll();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PolizaON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public void generarPoliza(Solicitud solicitud) {
        try {
            Poliza p = new Poliza();
            p.setId(p.getId());
            p.setMonto(solicitud.getMonto());
            p.setFrecuencia(solicitud.getFrecuencia());
            p.setPlazo(solicitud.getPlazo());
            p.setCuenta(solicitud.getCliente().getCuentaList().get(0));
            double interes= solicitud.getPlazo()*solicitud.getTasaInteres();
            p.setInteresGanado(interes);
            enviarCorreoIngreso(p);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PolizaON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviarCorreoIngreso(Poliza poliza) {
        String respesta = "<h1> Detalle Poliza </h1><br><bbr>";
        respesta = respesta + "<table>\n"
                + "  <tr>\n"
                + "    <th>Monto</th>\n"
                + "    <th>Frecuencia</th>\n"
                + "    <th>Plazo</th>\n"
                + "    <th>Interes Ganado</th>\n"
                + "  </tr>\n";
        //            for (Poliza p : poliza.getPlazo()) {
//                respesta = respesta + "<tr>\n";
//                respesta = respesta + "<td>" + a.getPeriodo() + "</td>\n"
//                        + "<td>" + a.getInteres() + "</td>\n"
//                        + "<td>" + a.getTotal() + "</td>\n"
//                        + "<td>" + a.getFecha() + "</td>\n";
//                respesta = respesta + " </tr>\n";
//
//
//            }
respesta = respesta + "</table>";
    }

 
}
