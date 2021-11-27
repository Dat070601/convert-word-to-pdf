package Model.BO;

import Model.DAO.UserDAO;

import java.util.List;

import Model.BEAN.User;

public class UserBO {
	public User FindUser(String username,String password){
		return new UserDAO().findOne(username, password);
	}
	public void InsertUser(User tk) {
		UserDAO dao = new UserDAO();
		dao.ThemTK(tk);
		
	}
}
