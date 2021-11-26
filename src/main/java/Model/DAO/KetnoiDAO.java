package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KetnoiDAO {
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/ltm";
			String username  = "root";
			String password ="";
			conn =DriverManager.getConnection(url,username, password);
	
		} catch (SQLException e) {
				e.printStackTrace();
			
		}
	     catch (ClassNotFoundException e) {
		  e.printStackTrace();
	}
		
		return conn;
	}

}
