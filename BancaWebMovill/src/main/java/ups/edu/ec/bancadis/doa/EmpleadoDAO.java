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
import ups.edu.ec.bancadis.modelo.Empleado;

/**
 *
 * @author Fanny
 */

@Stateless
public class EmpleadoDAO {
    
    @PersistenceContext(name="BancaWebMovillPersistenceUnit")
    private EntityManager em;

    public EmpleadoDAO() {
    }
     /**
     * Inserta el trabajador  mediante  el objeto de Trabajador.
     * @param trabajador
     * @return si inserto o no.
     */
    
    public boolean insert(Empleado  trabajador) throws Exception {
        boolean bandera = true;
    	try {
            System.out.println("si creo que llega aca");
            em.persist(trabajador);
            bandera=true;
        } catch (Exception e) {
        	bandera=false;
            throw new Exception("Erro ingreso Trabajador " + e.getMessage());
            
        }
        
        return bandera;
    }
      /**
     * Elimina el metodo mediante el numero el objeto de Alogin.
     * @param trabajador
     * @remove
     */
    public void delete(Empleado trabajador) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(trabajador.getId()));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Trabajador " +e.getMessage());
        }
    }
    /**
     * Elimina el metodo mediante el id.
     * @param trabajador
     * @remove
     */
    public void deleteId(int id) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(id));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Trabajador " +e.getMessage());
        }
    }
          /**
     * Actualiza  el metodo mediante el objeto de Alogin
     * @param trabajador
     * @merge
     */

    public void update(Empleado trabajador) throws Exception {
        try {
            em.merge(trabajador);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Trabajador " +e.getMessage());
        }
    }
      /**
     * Lee  el metodo mediante el id
     * @param id
     * @find
     */

    public Empleado read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Empleado.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Trabajador " +e.getMessage());
        }
    }
    
       /**
     * El metodo lista todos los trabajadores
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    public List<Empleado> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Empleado.findAll");
            List<Empleado> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Trabajador " +e.getMessage());
        }

    }
             /**
     * El metodo lista todos los trabajadores realizados mediante el codigo
     * @param codigo
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
//    
//    public List<Empleado> findAllCodigo(String codigo) throws Exception {
//
//        try {
//            Query q = em.createNamedQuery("Empleado.findAllCodigo");
//            q.setParameter("codigo",  "%" + codigo + "%");
//            List<Empleado> lista = q.getResultList();
//            return lista;
//        } catch (Exception e) {
//            throw new Exception("Erro listar Trabajador " +e.getMessage());
//        }
//    }
               /**
     * El metodo busca mediante el id
     * @param  listarid
     * @createNamedQuery crea un querry para poder encontrar el objeto buscado
     * @return
     */
    
    public Empleado findByID(String id) throws Exception {
        try {
            Query q = em.createNamedQuery("Empleado.findById");
            q.setParameter("id", Integer.parseInt(id));
            return (Empleado) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  ID ");
        }
    }
 
             /**
     * El metodo buscar mediante la cedula
     * @param cedula
     * @createNamedQuery crea un querry para poder buscar la sentencia ante la clase
     * @return
     */
    public Empleado findByCedula(String cedula) throws Exception {
        try {
            String jpql = "SELECT P FROM Empleado p "
                    + "WHERE cedula = :cedula";
            Query q = em.createQuery(jpql, Empleado.class);
            q.setParameter("cedula", cedula);

            return (Empleado) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  cedula");
        }

    }
             /**
     * El metodo selecciona el maximo de un id
     * @createQuery esta sentencia recibe el querry y la clase
     * @return
     */
    
    public int maxId() throws Exception {
        try {
            String jpql = "SELECT P FROM Empleado p "
                    + "WHERE cedula = :cedula";
            Query q = em.createQuery(jpql, Empleado.class);
            return (int) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Error MaxID", e.getCause());
        }
    }
    
      public List<Empleado> findAllCodigo(String codigo) throws Exception {

        try {
            Query q = em.createNamedQuery("Empleado.findAllCodigo");
            q.setParameter("codigo",  "%" + codigo + "%");
            List<Empleado> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Trabajador " +e.getMessage());
        }

    }
    
}
