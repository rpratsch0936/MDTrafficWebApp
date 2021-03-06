

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/SpeedServlet"})
public class SpeedServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter(); 
            /* TODO output your page here. You may use following sample code. */
      
        out.println("<a href=LogoutServlet>Logout</a>"); 
        out.println("<a href=second.html>Back</a>");
        out.println("<h1 style=\"color: teal;\">Speed Sensors </h1>"); 
         Connect c;
        try {
            
            c = new Connect();
            Connection con= c.makeConnection();
            System.out.println("Connectionblished");
            Statement stmt = null;
            ResultSet rs = null;
            
            String SQL = "SELECT * FROM speeds";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
               
                  out.println("<p>Location: "+rs.getString("location") +"</p>"); 
                  out.println("<p>DeviceID: " + rs.getString("deviceID") +"</p>");
                  out.println("<p>Speed: " + rs.getString("speed") +"</p>");
                  out.println("<p>Time Reported: " + rs.getString("timeReported") +"</p>");
                  out.println("<p>Direction: " + rs.getString("direction") +"</p>");
                  out.println("<p>Latitude: " + rs.getString("latitude") +"</p>");
                  out.println("<p>Longitude: " + rs.getString("longitude") +"</p>");
                  out.println("</br>");
                  
            }
         
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
               
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
