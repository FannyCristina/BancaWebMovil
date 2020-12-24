/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.bancadis.doa;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ups.edu.ec.bancadis.modelo.Poliza;

/**
 *
 * @author Fanny
 */

@Stateless
public class PolizaDAO {
    
     @PersistenceContext(name="BancaWebMovillPersistenceUnit")
    private EntityManager em;

    public PolizaDAO() {
    }
     /**
     * Inserta el login mediante el numero el objeto de transferencias.
     * @param transferencias
     * @return si inserto o no.
     */
    
    public boolean insert(Poliza poliza) throws Exception {
        boolean bandera = true;
    	try {
            System.out.println("si creo que llega aca");
            em.persist(poliza);
            bandera=true;
        } catch (Exception e) {
        	bandera=false;
            throw new Exception("Erro ingreso Transferencias " + e.getMessage());
            
        }
        
        return bandera;
    }
      /**
     * Elimina el metodo mediante el numero el objeto de transferencias.
     * @param transferencias
     * @remove
     */
    public void delete(Poliza poliza) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(poliza.getId()));
        } catch (Exception e) {
            throw new Exception("Erro Eliminar Transferencias " +e.getMessage());
        }
    }
        /**
     * Elimina el metodo mediante el id.
     * @param id
     * @remove
     */

    public void deleteId(int id) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(id));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Transferencias " +e.getMessage());
        }
    }
          /**
     * Actualiza  el metodo mediante el objeto de Alogin
     * @param alogin
     * @merge
     */

    public void update(Poliza poliza) throws Exception {
        try {
            em.merge(poliza);
        } catch (Exception e) {
            throw new Exception("Erro actualizar poliza " +e.getMessage());
        }
    }
      /**
     * Lee  el metodo mediante el id
     * @param id
     * @find
     */

    public Poliza read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Poliza.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Poliza " +e.getMessage());
        }
    }
    
       /**
     * El metodo lista todos los transferencias existentes
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    public List<Poliza> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Poliza.findAll");
            List<Poliza> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Transferencias " +e.getMessage());
        }

    }
             /**
     * El metodo lista todos los transferenciass por su codigo
     * @param codigo
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    
//    public List<Poliza> findAllCodigo(String codigo) throws Exception {
//
//        try {
//            Query q = em.createNamedQuery("Poliza.findAllCodigo");
//            q.setParameter("codigo",  "%" + codigo + "%");
//            List<Transferencias> lista = q.getResultList();
//            return lista;
//        } catch (Exception e) {
//            throw new Exception("Erro listar Transferencias " +e.getMessage());
//        }
//
//    }
               /**
     * El metodo busca todos los transferenciass mediante sus id
     * @param id
     * @createNamedQuery crea un querry para poder encontrar 
     * @return
     */
    
    public Poliza findByID(int id) throws Exception {
        try {
            Query q = em.createNamedQuery("Poliza.findById");
            q.setParameter("id", id);
            return (Poliza) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  ID " +e.getMessage());
        }

    }
    
    
//    public Poliza findByNuemor(String numero) throws Exception {
//        try {
//            Query q = em.createNamedQuery("Poliza.findByNumero");
//            q.setParameter("numero", numero);
//            return (Poliza) q.getSingleResult();
//        } catch (Exception e) {
//            throw new Exception("Erro buscar por  ID " +e.getMessage());
//        }
//
//    }
    
 
 
    
}
