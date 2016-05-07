

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

@WebServlet(urlPatterns = {"/IncidentsServlet"})
public class IncidentsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter(); 
            /* TODO output your page here. You may use following sample code. */
      
        out.println("<a href=LogoutServlet>Logout</a>"); 
        out.println("<a href=second.html>Back</a>");
        out.println("<h1 style=\"color: teal;\">Type One Closures </h1>"); 
         Connect c;
        try {
            
            c = new Connect();
            Connection con= c.makeConnection();
            System.out.println("Connectionblished");
            Statement stmt = null;
            ResultSet rs = null;
            
            String SQL = "SELECT * FROM typeoneclosures";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
               
                  out.println("<p> Permit: "+rs.getString("permit") +"</p>"); 
                  out.println("<p>Start Date: " + rs.getString("startDate") +"</p>");
                  out.println("<p>Finish Date: " + rs.getString("finishDate") +"</p>");
                  out.println("<p>Start Time: " + rs.getString("startTime") +"</p>");
                  out.println("<p>End Time: " + rs.getString("endTime") +"</p>");
                  out.println("<p>Permit Type: " + rs.getString("permitType") +"</p>");
                  out.println("<p>Reason: " + rs.getString("reason") +"</p>");
                  out.println("<p>County From: " + rs.getString("countyFrom") +"</p>");
                  out.println("<p>County To: " + rs.getString("countyTo") +"</p>");
                  out.println("<p>Route: " + rs.getString("route") +"</p>");
                  out.println("<p>Lanes: " + rs.getString("numLanes") +"</p>");
                  out.println("<p>Direction: " + rs.getString("direction") +"</p>");
                  out.println("<p>Location Limits: " + rs.getString("locationLimits") +"</p>");
                  out.println("<p>Days Affected: " + rs.getString("daysAffected") +"</p>");
                  out.println("<p>Lanes Closed: " + rs.getString("lanesClosed") +"</p>");
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
