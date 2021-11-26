package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.BEAN.User;

public class UserDAO {
	public User findOne(String username,String password){
		Connection conn = KetnoiDAO.getConnection();
		String sql ="select * from user where username=? and password=?";

		PreparedStatement ppst;
		try {
			ppst = conn.prepareStatement(sql);
			ppst.setString(1, username);
			ppst.setString(2, password);
			ResultSet rs = ppst.executeQuery();
			if (rs.next()){
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setId(rs.getInt("ID"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	public void ThemTK(User tk) {
		Connection conn = null;
		PreparedStatement ppst = null;
		try {
			String sql ="insert into user(username,password) values(?,?)";
			conn = KetnoiDAO.getConnection();
			ppst= conn.prepareStatement(sql);
			ppst.setString(1,tk.getUsername());
			ppst.setString(2,tk.getPassword());
			ppst.executeUpdate();
		} catch (SQLException e) {
			if (conn!= null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
			e.printStackTrace();

		}
		finally {
			try {
			if (conn!=null) {
				
					conn.close();
			}
			
			if (ppst!=null) {
				ppst.close();
			}
			
				} catch (SQLException e) {
					
					e.printStackTrace();
			}
		}
		
	}

}
