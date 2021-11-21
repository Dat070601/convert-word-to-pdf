package Model.BO;

import java.util.ArrayList;

import Model.BEAN.Wife;
import Model.DAO.CheckLoginDAO;

public class CheckLoginBO {
	CheckLoginDAO checkLoginDAO = new CheckLoginDAO();
	public boolean isValidUser(String name, String password)
	{
		return checkLoginDAO.isExistUser(name, password);
	}
	
	public ArrayList<Wife> getListWife(String name)
	{
		return checkLoginDAO.getWifeList(name);
	}
}
