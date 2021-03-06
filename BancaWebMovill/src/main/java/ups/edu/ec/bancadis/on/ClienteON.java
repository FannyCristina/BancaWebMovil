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
import ups.edu.ec.bancadis.doa.ClienteDAO;
import ups.edu.ec.bancadis.modelo.Cliente;
import ups.edu.ec.bancadis.modelo.ClienteTem;
import ups.edu.ec.bancadis.modelo.Cuenta;
import ups.edu.ec.bancadis.modelo.Transaciones;

/**
 *
 * @author Fanny
 */

@Stateless
public class ClienteON {
    
     @Inject
     ClienteDAO clienteDAO;

    @Inject
    CorreoON correoON;
    
    /**
     * Permite consumir la logica de transaccionesON
     */
    @Inject
    TransaccionesON transaccionesON;

    public ClienteON() {
    }

    /**
     * Retorna un parametro c donde se concatena un day, segundo y una palabra
     * para formar la cuenta de la persona.
     *
     * @return
     */
    public String numeroCuenta() {
        String c = "";
        try {
            int cuenta = clienteDAO.maxId();
            c = "numero" + cuenta + new Date().getDay() + "S" + new Date().getSeconds();
        } catch (Exception ex) {
            c = "numero" + 0 + new Date().getDay() + "S" + new Date().getSeconds();
            System.out.println(c);
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    /**
     * Se recibe como parametro al objeto cliente para proceder a actualizar
     *
     * @param cliente
     */
    public void actualizarCliente(Cliente cliente) {
        try {
            clienteDAO.update(cliente);
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /**
     * Se recibe tres parametros, donde el cliente es la persona quien realiza
     * la transaccion Tipo se refiere a que tipo de transaccion se realizo
     * Cantidad es el monto con el que se realizo la transaccion
     *
     * @param cliente
     * @param tipo
     * @param cantidad
     */
    public void actualizarClienteTrasaccion(Cliente cliente, String tipo, double cantidad) {
        try {
            clienteDAO.update(cliente);
            System.out.println("Cliente acutalizado " + cliente.getCuentaList().get(0).getSaldo());
            Transaciones transacion = new Transaciones();

            transacion.setCantidad(String.valueOf(cantidad));
            
            transacion.setCuentaid(cliente.getCuentaList().get(0));
            transacion.setFecha(new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate()));
            transacion.setTipo(tipo);
            System.out.println("Cliente acutalizado " + cliente.getCuentaList().get(0).getSaldo());
            transaccionesON.guardarTransaciones(transacion);

        } catch (Exception ex) {
            System.out.println("Error: ex " + ex.getMessage());
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
    public Cliente buscarCliente(String id) {
        try {
            return clienteDAO.findByID(id);
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    /**
     * Este metodo nos ayuda a buscar el cliente por cedula, para poder ser
     * consumido desde el controllerBean
     *
     * @param cedula
     * @return
     */
    public Cliente buscarClienteCedula(String cedula) {
        try {
            return clienteDAO.findByCedula(cedula);
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Metodo para mostrar el listado de clientes busca todos loc clientes
     * coincidentes
     *
     * @return
     */
    public List<Cliente> listaClientees() {
        try {
            return clienteDAO.findAll();
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Tenemos una lista de clientes mediante el codigo que pasamos como
     * parametro
     *
     * @param codigo
     * @return
     */
    public List<Cliente> listaClienteesCodigo(String codigo) {
        try {
            return clienteDAO.findAllCodigo(codigo);
        } catch (Exception ex) {
            Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //>>>>>>>>>>>>>>>>>>
    
    public boolean findByEmail(ClienteTem clientetemp) throws Exception {
    	//String cedula, String codigoemail
    	//try {
    		Cliente clienteCed = clienteDAO.findByCedula(clientetemp.getCedula());
    		Cliente clientEmail = clienteDAO.findByEmail(clientetemp.getEmail());
    		System.out.println(" >>>>> EMAil recuperado "+clientEmail.getCorreo() + "  Cedula re "+ clienteCed.getCedula());
    		if(clienteCed != null && clientEmail != null) {
    			actContraCliente(clienteCed);
      			 return true; 
    		}else return false;
           
       // } catch (Exception ex) {
        //	return false;
           // Logger.getLogger(ClienteON.class.getName()).log(Level.SEVERE, null, ex);
       // }
		
    }
    
    
    
    /**
     * Metodo que permite actualizar la contrasena, cuando se esta ya en la cuenta
     * permite cambiar cuando se esta en la interfaz del usuario
     * @param cliente
     * @throws Exception
     */
    
    public void  actualizarContrasenaCliente(Cliente cliente) throws Exception {
    	cliente.setContrasena(correoON.contrasenaAleatoria());
    	try {
    		cliente.setContrasena(correoON.contrasenaAleatoria());
    		cliente.setActivo(true);
			clienteDAO.update(cliente);
			
			String correo = cliente.getCorreo();
			String nuevapsw = cliente.getContrasena();
			
			correoON.sendAsHtml(correo, "Actualizacion de contrasena", "<h1>Su contrasena han sido actualizados: </h1>" + nuevapsw);
		} catch (Exception e) {
			throw new Exception("No se puede actualizar");
		}
    }
    

    /**
     * Este metodo nos permite guardar el cliente y tambien una cuenta asociada
     * al cliente Se realiza la validadcion de la cedula en el caso de ser
     * validas nos permite guardar Tambien se realiza una vakidacion para poder
     * enviar correos con el usuario y contrasena correcta
     *
     * @param cliente
     * @param cuenta
     * @return 
     * @throws Exception
     */
    public void  actContraCliente(Cliente cliente) throws Exception {
        cliente.setContrasena(correoON.contrasenaAleatoria());
        try {
            cliente.setContrasena(correoON.contrasenaAleatoria());
            cliente.setActivo(true);
            clienteDAO.update(cliente);
            // enviar correo 
            String correo = cliente.getCorreo();
            String nuevacontr = cliente.getContrasena();
            correoON.sendAsHtml(correo, "Actualizacion Contrasena", "<h1> Su contrasena es: </h1>" + nuevacontr);
            
        } catch (Exception e) {
        	throw new Exception("No se puede actualizar");
        }

    }

    public boolean guardarCliente(Cliente cliente, String cuenta) throws Exception {
        if (validarCedula(cliente.getCedula())) {
            try {
                System.out.println(cliente.getId() + " Este es el ID");
            	String contrasena=correoON.contrasenaAleatoria();
                cliente.setContrasena(contrasena);
                cliente.setActivo(true);
                cliente.setEliminado(false);
                cliente.setId(null);
                
                Cuenta cu = new Cuenta();
                cu.setNumerocuenta(cuenta);
                cu.setSaldo(0);
                cu.setFechaapertura(new Date(new Date().getYear(), new Date().getMonth(), new Date().getDay()));
                cu.setElimado(false);
                cu.setCliente(cliente);
                cu.setTipoCuenta("Ahorro");
                List<Cuenta> listaCu = new ArrayList<>();
                listaCu.add(cu);
                cliente.setCuentaList(listaCu);
               
                
                boolean respuesta = clienteDAO.insert(cliente);
                String correo = cliente.getCorreo();
                if (respuesta == true) {
                	correoON.sendAsHtml(correo, "Bienvenido a JGCBANK®", "<h2>Estimado cliente usted puede ingresar con: </h2><p>Sus Datos son : </p>Su usuario es: " 
                                + cliente.getCedula() + " Su Contrasena: " + cliente.getContrasena()+ ""
                            + " <h4> RECUERDE ESTIMADO CLIENTE, CAMBIAR LA CONTRASENA POR SU SEGURIDAD.</h4><br> <h4>Cuenca-Ecuador</h4>");
                } else {
                	correoON.sendAsHtml(correo, "No se registro", "Datos incompletos");
                }
                cliente = new Cliente();
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        } else {
            throw new Exception("Cedula Incorrecta");
        }
        return true;
    }

    /**
     * Este metodo es para validar la cedula si esta es valida nos retorna true
     * CAso contrario nos devuelve un false Recibe como parametro la cedula
     *
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
