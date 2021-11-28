package Model.BO;

import Model.DAO.UserDAO;

import java.util.List;

import Model.BEAN.User;

public class UserBO {
	public User FindUser(String username,String password) {
		if ("".equals(username) || "".equals(password))
			return null;
		return new UserDAO().FindUser(username, password);
	}
	public void InsertUser(User user) {
		new UserDAO().InsertUser(user);
		
	}
}
