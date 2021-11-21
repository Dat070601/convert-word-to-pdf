package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.Wife;
import Model.BO.CheckLoginBO;

/**
 * Servlet implementation class CheckLoginServlet
 */
@WebServlet("/CheckLoginServlet")
public class CheckLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String des = null;
		String userName= request.getParameter("userName");
		String password = request.getParameter("password");
		
		CheckLoginBO checkLoginBO = new CheckLoginBO();
		
		ArrayList<Wife> wifeArray= null;
		if(checkLoginBO.isValidUser(userName, password))
		{
			
			wifeArray = checkLoginBO.getListWife(userName);
			request.setAttribute("wifeArray", wifeArray);
			des = "/HomePage.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(des);
			rd.forward(request, response);
		}
		else
		{
			des="/Login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(des);
			rd.forward(request, response);
		}
	}

}
