
import com.mysql.jdbc.Driver;
import java.sql.*;

public class Connect {
    public Connect() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
      //  makeConnection();
    } 

    private Connection koneksi;  

     public  Connection makeConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (koneksi == null) {
             new Driver();
             String url="jdbc:mysql://localhost:3306/trafficdb";
             String userName="regina";
             String password="Tiesto1!";
             Class.forName("com.mysql.jdbc.Driver").newInstance();

             koneksi=DriverManager.getConnection(url,userName,password);
         }
         return koneksi;
     }  

   
     public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
         try {
             Connect c = new Connect();
             Connection con= c.makeConnection();
             System.out.println("Connectionblished");
             Statement stmt = null;
             ResultSet rs = null;
           
             String SQL = "SELECT * FROM login";
             stmt = con.createStatement();
             rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("Username") + " : " + rs.getString("password"));
            }
         }
         catch (SQLException e) {
             e.printStackTrace();
             System.err.println("Connectionure");
         }  
         

    }
}