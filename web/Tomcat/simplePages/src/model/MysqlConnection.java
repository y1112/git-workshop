package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class MysqlConnection {
	public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String pass="yourpasswd";
        
        Connection conn = null;
        
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            System.out.println("����̹� �˻� ����!");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("���� ����"+conn);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("����̹� �˻� ����!");
            e.printStackTrace();
        }
        //
        catch (SQLException e) {
            // TODO Auto-generated catch block
            
            e.printStackTrace();
        }
    }
 
}