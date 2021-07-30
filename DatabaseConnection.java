
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	
	Connection conn = null;
	java.sql.PreparedStatement pst;
	
	public static Connection dbconnet()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shree_krishna_bank","root","");
			return conn;
		} catch (Exception e2) {
			
			System.out.println(e2);
			return null;
			
		}
		
	}

}
