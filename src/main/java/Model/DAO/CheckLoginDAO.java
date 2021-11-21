package Model.DAO;
import java.sql.Connection;
import java.sql.*;
import java.util.*;


import Model.BEAN.Wife;
public class CheckLoginDAO {
	
	public static Connection conn = null;
	public static Connection getconnect()
	{
		String username="root";
		String password1="";
		String url ="jdbc:mysql://localhost:3306/ltm";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,username, password1);
			System.out.println("done");
			}
		catch (SQLException e) {
				e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public boolean isExistUser(String userName, String password)
	{
		if(getconnect() != null)
		{
			PreparedStatement pt = null;
			
			try {
				String sql = "select * from user where username=? and password=?";
				pt = conn.prepareStatement(sql);
				pt.setString(1,userName);
				pt.setString(2, password);
				ResultSet rs = pt.executeQuery();
				String name="";
				int alive;
				String pass="";
					while(rs.next())
					{
						 name=rs.getString("username");
						 alive = rs.getInt("IDuser");
						 pass =rs.getString("password");
					}
					if(name.equals(userName) && pass.equals(password))
					{
								return true;
					}
					else
					{
						return false;
						
					}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public ArrayList<Wife> getWifeList(String userName)
	{
		
		ArrayList<Wife> list = new ArrayList<Wife>();
		if(getconnect() != null)
		{
			PreparedStatement pt = null;
			
			try {
				String sql = "select * from user";
				pt = conn.prepareStatement(sql);
				ResultSet rs = pt.executeQuery();
	
				while(rs.next())
				{
					Wife wife = new Wife();
					String name=rs.getString("username");
					int alive = rs.getInt("IDuser");
					String pass =rs.getString("password");
					wife.setName(name);
					wife.setAlive(alive);
				//	wife.setPassword(pass);
					list.add(wife);
				}
				pt.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return list;
	}
}
