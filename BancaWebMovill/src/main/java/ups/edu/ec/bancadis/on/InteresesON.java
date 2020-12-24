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
import ups.edu.ec.bancadis.doa.EmpleadoDAO;
import ups.edu.ec.bancadis.doa.InteresesDAO;
import ups.edu.ec.bancadis.modelo.Cliente;
import ups.edu.ec.bancadis.modelo.ClienteTem;
import ups.edu.ec.bancadis.modelo.Cuenta;
import ups.edu.ec.bancadis.modelo.Intereses;
import ups.edu.ec.bancadis.modelo.Transaciones;

/**
 *
 * @author Fanny
 */
@Stateless
public class InteresesON {

    @Inject
    InteresesDAO interesesDAO;

    public double interese(int numero_mesas, double valor) {
        double valor_ganado = 0;
        if (numero_mesas >= 1 && numero_mesas <= 2) {
            valor_ganado = valor * 0.55 + valor;
        }
        if (numero_mesas >= 3 && numero_mesas <= 4) {
            valor_ganado = valor * 0.575 + valor;
        }
        if (numero_mesas >= 3 && numero_mesas <= 8) {
        }
        return valor_ganado;
    }

    public InteresesON() {
        
    }


    /**
     * Se recibe como parametro al objeto cliente para proceder a actualizar
     *
     * @param cliente
     */
    public void actualizarInteres(Intereses intereses) {
        try {
            interesesDAO.update(intereses);
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    /**
     * Este metodo nos ayuda a buscar el cliente por id, para poder ser
     * consumido desde el controllerBean
     *
     * @param id
     * @return
     */
    public Intereses buscarInteres(String id) {
        try {
            return interesesDAO.findByID(id);
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }


}
