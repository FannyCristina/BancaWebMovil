/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.bancadis.on;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import ups.edu.ec.bancadis.doa.EmpleadoDAO;
import ups.edu.ec.bancadis.modelo.Empleado;

/**
 *
 * @author Fanny
 */
@Stateless
public class EmpleadoON {
     @Inject
     EmpleadoDAO empleadoDAO;
    
    

    public EmpleadoON() {
    }
    /**
     * Se actualiza datos del trabajador, esta logica es consumida desde 
     * la interfaz web
     * @param empleado
     */
    public void actualizarTrabajador(Empleado empleado){
        try {
            empleadoDAO.update(empleado);
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Permite buscar el trabajador mediante el id 
     * @param id
     * @return
     */
    public Empleado buscarTrabajador(String id){
        try {
            return empleadoDAO.findByID(id);
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
   
    /**
     * Se manda a buscar la lista del trabajador 
     * @return
     */
    public List<Empleado> listaTrabajadores(){
        try {
            return empleadoDAO.findAll();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Permite buscar por codigo y rel resultado se almacena en una lista de trabajador
     * @param codigo
     * @return
     */
    public List<Empleado> listaTrabajadoresCodigo(String codigo){
        try {
            return empleadoDAO.findAllCodigo(codigo);
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Permite resgistrar al trabajador y enviarle un usuario y la contrasena 
     * para que pueda acceder a su cuenta 
     * @param trabajador
     * @return
     * @throws Exception
     */
    public boolean guardarTrabajador(Empleado empleado) throws Exception {
        if (validarCedula(empleado.getCedula())) {
            try {
            
            	CorreoON c = new CorreoON();
            	String contrasena=c.contrasenaAleatoria();
                empleado.setContracenia(contrasena);
                empleado.setActivo(true);
                empleado.setEliminado(false);
                empleado.setId(null);
                
                boolean respuesta=empleadoDAO.insert(empleado);
                
                String correo=empleado.getCorreo();
                if (respuesta==true) {
					c.sendAsHtml(correo, "Bienvenido a SimonBank®", "<h2>Estimado cliente usted puede ingresar con: </h2><p>Sus Datos son : </p>Su usuario es: "+empleado.getCedula()+" Su Contrasena: "+empleado.getContracenia()+""
							+ " <h4> RECUERDE ESTIMADO CLIENTE, CAMBIAR LA CONTRASENA POR SEGURIDAD.</h4><br> <h4>Cuenca-Ecuador</h4>");
				}else {
					c.sendAsHtml(correo, "No se registro", "Datos incompletos");
				}
                
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        } else {
            throw new Exception("Cedula Incorrecta");
        }

        return true;
    }
    
    /**
     * Este metodo nos permite validar la cedula e
     * en caso de ser valida devuelve un true
     * @param ced
     * @return
     */

    public boolean validarCedula(String ced) {
        boolean verdadera = false;
        int num = 0;
        int ope = 0;
        int suma = 0;
        for (int cont = 0; cont < ced.length(); cont++) {
            num = Integer.valueOf(ced.substring(cont, cont + 1));
            if (cont == ced.length() - 1) {
                break;
            }
            if (cont % 2 != 0 && cont > 0) {
                suma = suma + num;
            } else {
                ope = num * 2;
                if (ope > 9) {
                    ope = ope - 9;
                }
                suma = suma + ope;
            }
        }
        if (suma != 0) {
            suma = suma % 10;
            if (suma == 0) {
                if (suma == num) {
                    verdadera = true;
                }
            } else {
                ope = 10 - suma;
                if (ope == num) {
                    verdadera = true;
                }
            }
        } else {
            verdadera = false;
        }
        return verdadera;
    }

    public void enviarCorreo() {
    	
    }
}
