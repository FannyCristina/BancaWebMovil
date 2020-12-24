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
import ups.edu.ec.bancadis.modelo.Intereses;

/**
 *
 * @author Fanny
 */

@Stateless
public class InteresesDAO {
    
     @PersistenceContext(name="BancaWebMovillPersistenceUnit")
    private EntityManager em;

    public InteresesDAO() {
    }
     /**
     * Inserta el trabajador  mediante  el objeto de Trabajador.
     * @param trabajador
     * @return si inserto o no.
     */
    
    public boolean insert(Intereses intereses) throws Exception {
        boolean bandera = true;
    	try {
            System.out.println("si creo que llega aca");
            em.persist(intereses);
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
    public void delete(Intereses intereses) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(intereses.getId()));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar intereses " +e.getMessage());
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

    public void update(Intereses intereses) throws Exception {
        try {
            em.merge(intereses);
        } catch (Exception e) {
            throw new Exception("Erro actualizar intereses " +e.getMessage());
        }
    }
      /**
     * Lee  el metodo mediante el id
     * @param id
     * @find
     */

    public Intereses read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Intereses.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Intereses " +e.getMessage());
        }
    }
    
       /**
     * El metodo lista todos los trabajadores
     * @createNamedQuery crea un querry para poder listar
     * @return
     */
    public List<Intereses> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Intereses.findAll");
            List<Intereses> lista = q.getResultList();
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
    
//    public List<Intereses> findAllCodigo(String codigo) throws Exception {
//
//        try {
//            Query q = em.createNamedQuery("Trabajador.findAll");
//            q.setParameter("codigo",  "%" + codigo + "%");
//            List<Intereses> lista = q.getResultList();
//            return lista;
//        } catch (Exception e) {
//            throw new Exception("Erro listar Trabajador " +e.getMessage());
//        }
//
//    }
               /**
     * El metodo busca mediante el id
     * @param  listarid
     * @createNamedQuery crea un querry para poder encontrar el objeto buscado
     * @return
     */
    
    public Intereses findByID(String id) throws Exception {
        try {
            Query q = em.createNamedQuery("Intereses.findById");
            q.setParameter("ID", Integer.parseInt(id));
            return (Intereses) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  ID ");
        }

    }

             /**
     * El metodo selecciona el maximo de un id
     * @createQuery esta sentencia recibe el querry y la clase
     * @return
     */
    
    public int maxId() throws Exception {
        try {
            String jpql = "SELECT P FROM Intereses p "
                    + "WHERE cedula = :cedula";
            Query q = em.createQuery(jpql, Intereses.class);
            return (int) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Error MaxID", e.getCause());
        }
    }
    
}
