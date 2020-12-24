/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.bancadis.on;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import ups.edu.ec.bancadis.doa.CuentaDAO;
import ups.edu.ec.bancadis.doa.ResumenCuentaDAO;
import ups.edu.ec.bancadis.modelo.Cliente;
import ups.edu.ec.bancadis.modelo.Transaciones;

/**
 *
 * @author Fanny
 */

@Stateless
public class ResumenCuentaON {
   
     @Inject
     ResumenCuentaDAO resumenCuentaDAO;
    /**
     * Obtiene el resumen de todas las cuentas mediante el numero de cuenta.
     * @param numero
     * @return
     */
public List<Transaciones> getResumenCuentaCliente(String numero){
		return resumenCuentaDAO.getResumenCuentaCliente(numero);
    }
	/**
	 * Obtnemos el cliente medinte la cedula
	 * @param filtro
	 * @return
	 */
public List<Cliente> getClienteByCedelua(String filtro){
	return resumenCuentaDAO.getClienteByCedelua(filtro);
}
	/**
	 * Este metod indica los transacciones durante 30 dias.
	 * @param filtro
	 * @param fechaIni
	 * @param FechaFin
	 * @return
	 */
public List<Transaciones> getEstadoCtaByMes(String filtro, Date fechaIni, Date FechaFin){
	return resumenCuentaDAO.getEstadoCtaByMes(filtro, fechaIni, FechaFin);
}


}
