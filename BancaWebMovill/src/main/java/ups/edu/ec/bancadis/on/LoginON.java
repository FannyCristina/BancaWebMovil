/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.bancadis.on;

import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;
import ups.edu.ec.bancadis.doa.ClienteDAO;
import ups.edu.ec.bancadis.doa.EmpleadoDAO;
import ups.edu.ec.bancadis.doa.LoginDAO;
import ups.edu.ec.bancadis.modelo.Cliente;
import ups.edu.ec.bancadis.modelo.Empleado;
import ups.edu.ec.bancadis.modelo.Login;

/**
 *
 * @author Fanny
 */

@Stateless
public class LoginON {
    
    
    @Inject
    LoginDAO loginDAO;

    @Inject
    EmpleadoDAO empleadoDAO;

    @Inject
    ClienteDAO clienteDAO;
    
    @Inject
    CorreoON correoON;
    /**
     * Se realiza las validaciones como trabajador, mediante la cedula y la contrasena
     * 
     * @param cedula
     * @param contrasenia
     * @return
     */
    public Empleado loginTra(String cedula, String contrasenia) {
        try {
            Empleado empleado = empleadoDAO.findByCedula(cedula);
            if (empleado != null) {

                if (empleado.getContracenia().equals(contrasenia)) {
                    //enviarCorreoIngreso(trabajador.getCorreo(), true, obtenerIp());
                    //Aditoria de login de trabajdor >> true
                    return empleado;
                } else {
                    //Aditoria de login de trabajdor >> false
                    //enviarCorreoIngreso(trabajador.getCorreo(), true, obtenerIp());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginON.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }
    /**
     * Se valida al cliente desde la interfaz de login, se realiza un envio de correo 
     * con la direccion ip desde donde se accedio.
     * @param cedula
     * @param contrasenia
     * @return
     */
    public Cliente loginClie(String cedula, String contrasenia) {
        try {
            Cliente cliente = clienteDAO.findByCedula(cedula);
            if (cliente != null) {

                if (cliente.getContrasena().equals(contrasenia)) {
                    guardarLogin(cliente, true);
                    
                    enviarCorreoIngreso(cliente.getCorreo(), true);
                    return cliente;
                } else {
                    guardarLogin(cliente, false);
                    enviarCorreoIngreso(cliente.getCorreo(), false);
                    return null;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginON.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }
    /**
     * Se actualiza datos del trabajador, se realiza un envio de correo 
     * indicando que fue actualizado.
     * @param cedula
     * @param contrasenia
     * @return
     */
    public void actualizarTrabajador(Empleado empleado) {
        try {
            empleadoDAO.update(empleado);
            enviarCorreoUpdate(empleado.getCorreo());
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Se actualiza datos del cliente, se realiza un envio de correo 
     * indicando que fue actualizado.
     * @param cliente
     */
    public void actualizarCliente(Cliente cliente) {
        try {
            clienteDAO.update(cliente);
            enviarCorreoUpdate(cliente.getCorreo());
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Se registra el usuario y tambien el acceso con la ip , la fecha que trato de acceder
     * @param cliente
     * @param acceso
     */
    public void guardarLogin(Cliente cliente, boolean acceso) {
        Login login = new Login();
        try {
            login.setAcceso(acceso);
            login.setCliente(cliente);
            login.setFecha(new Date(new Date().getYear(), new Date().getMonth(), new Date().getDay()));
            loginDAO.insert(login);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    /**
     * Busca login mediante la cedula 
     * @param cedula
     * @return
     */
    public List<Login> listaLogin(String cedula){
        try {
            System.out.println("aqui");
            return loginDAO.findAllbyCedula(cedula);
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Permite enviar un correo cuando se accede a la cuenta, se adjunta al correo  la direccion ip
     * Permite enviar un correo cuando se intenta  ingresar a  la cuenta y falla, se adjunta al correo  la direccion ip
     * @param correo
     * @param acceso
     * @param ip
     */
    public void enviarCorreoIngreso( String correo, boolean acceso) {
           	try {
           		
           		if (acceso==true) {
           			
           			correoON.sendAsHtml(correo, "Inicio Sesion en SimonBank®", "Usted ha ingresado a su cuenta desde La direccion ip : <p>IP : </p>");
				} else {
					correoON.sendAsHtml(correo, "Registro Sesion Fallido en SimonBank®", "Desde La direccion ip : <p>IP : </p>");

				}
           		
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
    	//El correo viene en la variable correo
        //(acceso = true ) >> enviar el correo con login correcto si es acceso es true
        //(acceso = false ) >> enviar el correo con: se registra un intento erroneo de acceso con su cuenta 
        // Ip >> lugar desde donde intentan usar
    }
    
    /**
     * Envia un correo indicando que la contrasena se actualizo satisfactoriamente
     * Este metodo es consumido en metodos anteriores.
     * @param correo
     */
    public void enviarCorreoUpdate(String correo){
    			
    			try {
    				correoON.sendAsHtml(correo, "Constrasena Actualizada", "<p>Se actualizo su contrasena </p>");
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        //enviar un correo diciendo se a autualizado la contra
        //No importa si es cliente o si es trabajdor
    }
    
}
