package Model.DAO;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	public File UploadFile(File file) {
		try {
			String query = "insert into storage(userid, filename, status, date, data) values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, file.getUserID());
			ps.setString(2, file.getFileName());
			ps.setBoolean(3, file.getStatus());
			ps.setDate(4, file.getDate());
			ps.setBlob(5, file.getData());
			if (ps.executeUpdate() != 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					file.setFileID(rs.getInt(1));
				}
				rs.close();
			}
			con.close();
			return file;
		} catch (Exception e) {
			System.out.println("DAO: " + e.toString());
			return null;
		}
	}
	
	public boolean UpdateFile(File file) {
		try {
			String query = "update storage set data=?, filename=?, status=? where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setBlob(1, file.getData());
			ps.setString(2, file.getFileName());
			ps.setBoolean(3, file.getStatus());
			ps.setInt(4, file.getFileID());
			if (ps.executeUpdate() != 0) {
				con.close();
				return true;
			} else {
				con.close();
				return false;
			}
		} catch (Exception e) {
			System.out.println("Update file error: " + e.toString());
			return false;
		}
	}
	
	public File GetFilePDFFromDB(int id) throws SQLException
	{
		String query = "Select * from Storage where id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1,id);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			String file_name  = rs.getString("FileName");
			int id_user = rs.getInt("UserID");
			Date date = rs.getDate("Date");
			Blob data = rs.getBlob("Data");
			boolean status = rs.getBoolean("Status");
			return new File(id, id_user, date, file_name, status, data);
		}
		else return null; // not exist in database
	 }
	
	 public void CloseConQuietly() {
	       try {
	           if (con != null) {
	               con.close();
	           }
	       } catch (Exception e) {}
	 }
}


