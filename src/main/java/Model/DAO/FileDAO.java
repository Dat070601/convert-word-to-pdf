package Model.DAO;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import Model.BEAN.File;

public class FileDAO {
	Connection con;
	public FileDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltm", "root", "");
		} catch (Exception e) {}
	}
	
	public Connection GetConnection()
	{
		return con;
	}
	
	public File GetFilePDFFromDB(Connection conn, int id) throws SQLException
	{
		String query = "Select * from Storage where id = ?";
		PreparedStatement pstm = conn.prepareStatement(query);
		pstm.setInt(1,id);
		ResultSet rs = pstm.executeQuery();
		if(rs.next())
		{
			String file_name  = rs.getString("FileName");
			int id_user = rs.getInt("UserID");
			Date date = rs.getDate("Date");
			Blob data = rs.getBlob("Data");
			int status = rs.getInt("Status");
			return new File(id, id_user, date, file_name, status, data);
		}
		else return null; // not exist in database
	}
}


