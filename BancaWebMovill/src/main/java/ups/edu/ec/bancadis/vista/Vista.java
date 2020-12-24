/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.bancadis.vista;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ups.edu.ec.bancadis.modelo.Cliente;
import ups.edu.ec.bancadis.on.ClienteON;

/**
 *
 * @author Fanny
 */

@WebServlet(name = "Vista", urlPatterns = {"/Vista"})
public class Vista extends HttpServlet {
    
    @Inject
    ClienteON cOn;
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.getWriter().println("<h1>Hola mundo, si estan sobreviviendo al covid as </h1>");

//             Mensajes e = servicesON.DepositoSRV(new DepositoSRV("CUHA06S3", 100));
//            Mensajes ee = servicesON.RetiroSRV(new RetiroSRV("CUHA06S3", 50));
//            response.getWriter().println("<h1>Hjuan: " + e.getNombre() + " <> "  + ee.getNombre() +"</h1>");
//            
//            Mensajes eee = servicesON.TransferenciasInternaSRV(new TransferenciaSRV("CUHA06S3", "CUHA16S10", 100.10, "Transferenacis internas"));
//            response.getWriter().println("<h1>Trans: " + eee.getNombre() +"</h1>");
//            response.getWriter().println(pythonON.predecirClienteCedula("0104600996"));

//            response.getWriter().println("<>>>>>>>")
Cliente c = new Cliente();
c.setNombres("Fanny");
c.setEliminado(true);
c.setApellido("Gutama");
c.setCedula("0107051534");
c.setContrasena("4422");
c.setCorreo("fannycrisssg@gmail.com");
c.setDireccion("BA");
c.setNumcelular("4042805");
c.setNumconvencional("0107051534");
c.setActivo(true);

            for (int i = 0; i < 10; i++) {
                
            }


                cOn.guardarCliente(c, "010705153482");
                System.out.println("Cliente ingresado"+c);

        } catch (Exception e) {
            response.getWriter().println(e.getMessage() + e.getLocalizedMessage() + " >>>");
            System.out.println(e.getMessage() + " acaca");
        }
    }
  
}
