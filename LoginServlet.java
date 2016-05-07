import java.io.IOException;  
import java.io.PrintWriter;  
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
public class LoginServlet extends HttpServlet {  
    
     public static final String SALT = "my-salt-text";
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                    throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        
      
        String name=request.getParameter("name");  
        String password=request.getParameter("password");  
        
        Connect c;
        String uname="", pwd="";
        try {
            
            c = new Connect();
            Connection con= c.makeConnection();
            System.out.println("Connectionblished");
            Statement stmt = null;
            ResultSet rs = null;
           
            String SQL = "SELECT * FROM login";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                uname=rs.getString("Username");
                pwd= rs.getString("password");
                
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
                
        String saltedPassword = SALT +  pwd;
        String hashedPassword = generateHash(saltedPassword);
        String saltedPwd = SALT + password;
        String hashedPwd = generateHash(saltedPwd);
        
         Statement stmt = null;
         ResultSet rs = null;   
         try{
            c = new Connect();       
            Connection con= c.makeConnection();
            
            String query = "update login set storedpwdhash = ? where username = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, hashedPassword);
            preparedStmt.setString(2, uname);
 
            // execute the java preparedstatement
             preparedStmt.executeUpdate();
       
            if(hashedPassword.equals(hashedPwd)){

            out.print("Welcome, "+name +"!");  
            HttpSession session=request.getSession();  
            session.setAttribute("name",name);  
            request.getRequestDispatcher("second.html").include(request, response);  
            }  
            else{  
                out.print("Sorry, username or password error!");  
                request.getRequestDispatcher("login.html").include(request, response);  
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
        out.close();  
    }  
    //SALT + Hashing
    
    public static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f' };
            for (int idx = 0; idx < hashedBytes.length;   idx++) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // handle error here.
        }

        return hash.toString();
    }
}  