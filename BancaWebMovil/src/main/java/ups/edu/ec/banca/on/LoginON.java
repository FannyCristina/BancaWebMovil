/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.banca.on;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import ups.edu.ec.banca.dao.TrabajadorDAO;
import ups.edu.ec.banca.modelo.Trabajador;

/**
 *
 * @author Fanny
 */

@Stateless
public class LoginON {
    
     @Inject
     TrabajadorDAO trabajadorDAO;
    
    /**
     * Recibe como parametros cedula y la contrasena para el inicio de sesion
     * Si la contrasena es correcta, permitira el ingreso al sistema  
     * @param cedula
     * @param contrasenia
     * @return
     */
    public Trabajador loginTra(String cedula, String contrasenia){
        try {
            Trabajador trabajador= trabajadorDAO.findByCedula(cedula);
            if(trabajador!=null){
                
                if(trabajador.getContracenia().equals(contrasenia)){
                    
                    //Enviar correo de login con ip
                    return trabajador;
                }else{
                    //enviar correo de login erroneo, alguien intenta usar su cedula para entrar
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginON.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
   
    
}
