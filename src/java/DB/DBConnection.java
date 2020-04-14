package DB;

    import java.sql.*;
    public class DBConnection {
    public Connection con=null;
    public Statement stmt=null;
    public ResultSet rst=null;
    public PreparedStatement pstmt=null;
    public DBConnection()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onappointment","root","");
        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
        
    }    
}
